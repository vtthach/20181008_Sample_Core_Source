package com.innovation.rain.feature.collection.signin.business.mapper;

import com.innovation.rain.feature.collection.signin.business.model.ClientSignInParam;
import com.innovation.rain.feature.collection.signin.business.model.ClientSignInRequest;
import com.innovation.rain.feature.collection.signin.business.model.ClientSignInResponse;
import com.innovation.rain.feature.collection.signin.business.model.ClientSignInUiModel;

import javax.inject.Inject;

public class ClientSignInMapperImpl implements ClientSignInMapper {

    @Inject
    public ClientSignInMapperImpl() {
        // Constructor injection
    }

    @Override
    public ClientSignInUiModel getUiModelFromResponse(ClientSignInResponse clientSignInResponse) {
        // TODO mapper implement here
        return new ClientSignInUiModel();
    }

    @Override
    public ClientSignInRequest getRequestFromParam(ClientSignInParam param) {
        // TODO mapper implement here
        return new ClientSignInRequest();
    }
}
