package com.sf0404.core.application.business.mapper;

import com.sf0404.core.application.business.usecase.BaseParam;

public interface BaseMapper<T, P extends BaseParam, E, R> {
    T getUiModelFromResponse(P param, R r);

    E getRequestFromParam(P param);
}
