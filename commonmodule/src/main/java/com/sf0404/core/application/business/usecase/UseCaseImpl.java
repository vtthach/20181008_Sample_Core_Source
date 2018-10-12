package com.sf0404.core.application.business.usecase;

import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.sf0404.core.application.business.core.callback.BaseCallBack;
import com.sf0404.core.application.business.core.exception.BaseException;
import com.sf0404.core.application.business.core.exception.BusinessErrorException;
import com.sf0404.core.application.business.core.exception.ErrorModel;
import com.sf0404.core.application.business.mapper.BaseMapper;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.HttpException;
import retrofit2.Response;
import timber.log.Timber;

/**
 * @param <T> UI Model likely is extended from {@link com.sf0404.core.application.business.model.BaseMapperResult}
 * @param <E> request type which is used for Repository
 * @param <R> response type which is return from Repository
 * @param <P> param type
 */
public abstract class UseCaseImpl<T, P extends BaseParam, E, R> implements UseCase<T, P>, BaseSubscriber.UseCaseCallback<T> {

    private final BaseMapper<T, P, E, R> mapper;
    protected BaseCallBack<T> callback;

    public UseCaseImpl(BaseMapper<T, P, E, R> mapper) {
        this.mapper = mapper;
    }

    protected abstract Observable<R> getRepositoryObservable(E requestFromParam);

    @Override
    public UseCase<T, P> setCallback(BaseCallBack<T> callback) {
        this.callback = callback;
        return this;
    }

    protected Disposable request(@NonNull Observable<T> rawObservable) {
        if (callback == null) {
            throw new RuntimeException("Callback must not null"); // NOSONAR
        }
        DisposableObserver<T> disposal = new BaseSubscriber<>(this);
        rawObservable.onErrorResumeNext(throwable -> {
            // TODO extract logic to a plugin class
            BaseException exception = null;
            if (throwable instanceof HttpException) {
                HttpException httpException = (HttpException) throwable;
                exception = new BusinessErrorException();
                Response<?> response = httpException.response();
                ResponseBody errorBody = response != null ? response.errorBody() : null;
                if (errorBody != null) {
                    ErrorModel errorModel = parseError(errorBody.toString());
                    if (errorModel != null) {
                        exception.setCode(errorModel.code);
                        exception.setMessage(errorModel.message);
                    }
                    exception.setHeaders(response.headers());
                    exception.setHttpCode(httpException.code());
                }
            }
            Timber.e("UseCaseImpl - OnErrorResumeNext: " + exception);
            // TODO generic exception here
            return Observable.error(exception != null ? exception : throwable);
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(disposal);

        return disposal;
    }

    private ErrorModel parseError(String jsonStr) {
        try {
            return new Gson().fromJson(jsonStr, ErrorModel.class);
        } catch (Exception e) {
            Timber.e("UseCaseImp - Parse ");
            return null;
        }
    }

    @Override
    public Disposable execute(P param) {
        return request(getObservable(param));
    }

    private Observable<T> getObservable(final P param) {
        return getRepositoryObservable(mapper.getRequestFromParam(param))
                .map(r -> mapper.getUiModelFromResponse(param, r));
    }

    @Override
    public void onSuccess(T t) {
        callback.onSuccess(t);
    }

    @Override
    @CallSuper
    public void onError(Throwable e) {
        onCommonError(e);
    }

    protected void onCommonError(Throwable e) {
        if (e instanceof BusinessErrorException) {
            onBusinessException((BusinessErrorException) e);
        } else {
            onUnknownError(e);
        }
    }

    /**
     * Child class to override
     *
     * @param e
     */
    protected void onBusinessException(BusinessErrorException e) {
        callback.onError(e);
    }

    /**
     * Child class to override
     *
     * @param e
     */
    protected void onUnknownError(Throwable e) {
        callback.onError(e);
    }

    @Override
    public void onStart() {
        callback.onStart();
    }

    @Override
    public void onFinish() {
        callback.onFinish();
    }
}
