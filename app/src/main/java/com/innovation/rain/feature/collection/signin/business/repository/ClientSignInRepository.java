package com.innovation.rain.feature.collection.signin.business.repository;


import com.innovation.rain.feature.collection.signin.business.model.ClientSignInRequest;
import com.innovation.rain.feature.collection.signin.business.model.ClientSignInResponse;

import io.reactivex.Observable;
import retrofit2.http.Body;

public interface ClientSignInRepository {
    Observable<ClientSignInResponse> post(@Body ClientSignInRequest request);
}
