package com.sf0404.core.application.business.usecase;


import com.sf0404.core.application.business.core.callback.BaseCallBack;

import io.reactivex.disposables.Disposable;

/**
 * @param <T> likely extended from {@link com.sf0404.core.application.business.model.BaseMapperResult}
 */
public interface UseCase<T, P extends BaseParam> {
    UseCase<T, P> setCallback(BaseCallBack<T> callback);

    Disposable execute(P param);
}
