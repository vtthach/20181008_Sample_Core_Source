package com.innovation.rain.feature.collection.simdispenser.business.repository;

import com.innovation.rain.feature.collection.simdispenser.business.model.DispenseRequest;
import com.innovation.rain.feature.collection.simdispenser.business.model.DispenseResponse;

import io.reactivex.Observable;
import retrofit2.http.Body;

public interface DispenseRepository {
    Observable<DispenseResponse> dispense(@Body DispenseRequest request);
}
