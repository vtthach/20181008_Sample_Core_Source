package com.innovation.rain.feature.collection.simdispenser.business.mapper;


import com.innovation.rain.feature.collection.simdispenser.business.model.DispenseParam;
import com.innovation.rain.feature.collection.simdispenser.business.model.DispenseRequest;
import com.innovation.rain.feature.collection.simdispenser.business.model.DispenseResponse;
import com.innovation.rain.feature.collection.simdispenser.business.model.DispenseUiModel;

import javax.inject.Inject;

public class DispenseMapperImpl implements DispenseMapper {

    @Inject
    public DispenseMapperImpl() {
        // Constructor injection
    }

    @Override
    public DispenseUiModel getUiModelFromResponse(DispenseParam param, DispenseResponse DispenseResponse) {
        // TODO mapper implement here
        return new DispenseUiModel();
    }

    @Override
    public DispenseRequest getRequestFromParam(DispenseParam param) {
        // TODO mapper implement here
        return new DispenseRequest();
    }
}
