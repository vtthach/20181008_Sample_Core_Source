package com.sf0404.core.application.business.mapper;

import com.sf0404.core.application.business.usecase.BaseParam;

public interface BaseMapper<UiModel, Param extends BaseParam, Request, Response> {
    UiModel getUiModelFromResponse(Response r);

    Request getRequestFromParam(Param p);
}
