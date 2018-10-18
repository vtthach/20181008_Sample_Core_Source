package com.innovation.rain.feature.collection.signin.business.repository;


import com.innovation.rain.feature.collection.signin.business.model.ClientSignInRequest;
import com.innovation.rain.feature.collection.signin.business.model.ClientSignInResponse;
import com.innovation.rain.feature.collection.signin.business.service.ClientSignInApiService;
import com.sf0404.core.application.business.BaseRepositoryImpl;

import javax.inject.Inject;

import io.reactivex.Observable;
import retrofit2.http.Body;

public class ClientSignInRepositoryImpl extends BaseRepositoryImpl<ClientSignInApiService> implements ClientSignInRepository {

    @Inject
    public ClientSignInRepositoryImpl(ClientSignInApiService service) {
        super(service);
    }

    @Override
    public Observable<ClientSignInResponse> post(@Body ClientSignInRequest request) {
        return service.post(request);
    }
}
