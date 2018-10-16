package com.sf0404.core.application.business.usecase;


import com.sf0404.core.application.business.core.callback.BaseCallBack;
import com.sf0404.core.application.business.model.BaseUiModel;

import io.reactivex.disposables.Disposable;

/**
 * @param <T> likely extended from {@link BaseUiModel}
 */
public interface UseCase<T extends BaseUiModel, P extends BaseParam, C extends BaseCallBack<T>> {
    UseCase<T, P, C> setCallback(C callback);

    Disposable execute(P param);
}
