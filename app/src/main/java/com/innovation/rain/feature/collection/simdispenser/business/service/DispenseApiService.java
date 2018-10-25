package com.innovation.rain.feature.collection.simdispenser.business.service;


import com.innovation.rain.app.constant.ApiConstants;
import com.innovation.rain.feature.collection.simdispenser.business.model.DispenseRequest;
import com.innovation.rain.feature.collection.simdispenser.business.model.DispenseResponse;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface DispenseApiService {

    @POST(ApiConstants.Url.SIM_CARD_DISPENSE)
    Observable<DispenseResponse> dispense(@Body DispenseRequest request);
}
