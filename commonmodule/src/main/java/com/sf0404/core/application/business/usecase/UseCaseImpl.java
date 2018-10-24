package com.sf0404.core.application.business.usecase;

import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.sf0404.core.application.business.core.callback.BaseCallBack;
import com.sf0404.core.application.business.core.exception.BaseException;
import com.sf0404.core.application.business.core.exception.BusinessErrorException;
import com.sf0404.core.application.business.core.exception.ErrorModel;
import com.sf0404.core.application.business.mapper.BaseMapper;
import com.sf0404.core.application.business.model.BaseUiModel;

import java.io.BufferedReader;
import java.io.InputStreamReader;

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
 * @param <T> UI Model likely is extended from {@link BaseUiModel}
 * @param <E> request type which is used for Repository
 * @param <R> response type which is return from Repository
 * @param <P> param type
 */
public abstract class UseCaseImpl<T extends BaseUiModel, P extends BaseParam, E, R, C extends BaseCallBack<T>>
        implements UseCase<T, P, C>, BaseSubscriber.UseCaseCallback<T> {

    private final BaseMapper<T, P, E, R> mapper;
    protected C callback;

    public UseCaseImpl(BaseMapper<T, P, E, R> mapper) {
        this.mapper = mapper;
    }

    protected abstract Observable<R> getRepositoryObservable(E requestFromParam);

    @Override
    public UseCase<T, P, C> setCallback(C callback) {
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
                    ErrorModel errorModel = parseError(getStringFromResponseBody(errorBody));
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

    private String getStringFromResponseBody(ResponseBody responseBody) {
        BufferedReader reader = null;
        StringBuilder sb = new StringBuilder();
        try {
            reader = new BufferedReader(new InputStreamReader(responseBody.byteStream(), "UTF8"));
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (java.io.IOException e) {
            Timber.e(e, "Get string error");
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (java.io.IOException e) {
                    Timber.e(e, "Get string error");
                }
            }
        }
        return sb.toString();
    }

    private ErrorModel parseError(String jsonStr) {
        try {
            return new Gson().fromJson(jsonStr, ErrorModel.class);
        } catch (Exception e) {
            Timber.e(e,"UseCaseImp - Parse ");
            return null;
        }
    }

    @Override
    public Disposable execute(P param) {
        return request(getObservable(param));
    }

    private Observable<T> getObservable(final P param) {
        return getRepositoryObservable(mapper.getRequestFromParam(param))
                .map(mapper::getUiModelFromResponse);
    }

    @Override
    public void onSuccess(T t) {
        callback.onSuccess(t);
    }

    @Override
    @CallSuper
    public void onError(Throwable e) {
        // TODO check generic exception if need // NOSONAR
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
        if (!handleBusiness(e)) {
            callback.onError(e);
        }
    }

    protected boolean handleBusiness(BusinessErrorException e) {
        return false;
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
