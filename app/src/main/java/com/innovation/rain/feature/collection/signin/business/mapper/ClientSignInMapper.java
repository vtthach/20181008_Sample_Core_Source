package com.innovation.rain.feature.collection.signin.business.mapper;

import com.innovation.rain.feature.collection.signin.business.model.ClientSignInParam;
import com.innovation.rain.feature.collection.signin.business.model.ClientSignInRequest;
import com.innovation.rain.feature.collection.signin.business.model.ClientSignInResponse;
import com.innovation.rain.feature.collection.signin.business.model.ClientSignInUiModel;
import com.sf0404.core.application.business.mapper.BaseMapper;

public interface ClientSignInMapper extends BaseMapper<ClientSignInUiModel, ClientSignInParam, ClientSignInRequest, ClientSignInResponse> {

    @Override
    ClientSignInUiModel getUiModelFromResponse(ClientSignInParam param, ClientSignInResponse clientSignInResponse);

    @Override
    ClientSignInRequest getRequestFromParam(ClientSignInParam param);
}
