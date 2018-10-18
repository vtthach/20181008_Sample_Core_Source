package com.innovation.rain.feature.collection.signin.business.service;


import com.innovation.rain.app.constant.ApiConstants;
import com.innovation.rain.feature.collection.signin.business.model.ClientSignInRequest;
import com.innovation.rain.feature.collection.signin.business.model.ClientSignInResponse;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ClientSignInApiService {

    @POST(ApiConstants.Url.CLIENT_LOGIN)
    Observable<ClientSignInResponse> post(@Body ClientSignInRequest request);
}
