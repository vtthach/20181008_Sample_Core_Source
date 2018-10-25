package com.innovation.rain.feature.collection.simdispenser.business.mapper;


import com.innovation.rain.feature.collection.simdispenser.business.model.DispenseParam;
import com.innovation.rain.feature.collection.simdispenser.business.model.DispenseRequest;
import com.innovation.rain.feature.collection.simdispenser.business.model.DispenseResponse;
import com.innovation.rain.feature.collection.simdispenser.business.model.DispenseUiModel;
import com.sf0404.core.application.business.mapper.BaseMapper;

public interface DispenseMapper extends BaseMapper<DispenseUiModel, DispenseParam, DispenseRequest, DispenseResponse> {

    @Override
    DispenseUiModel getUiModelFromResponse(DispenseResponse DispenseResponse);

    @Override
    DispenseRequest getRequestFromParam(DispenseParam param);
}
