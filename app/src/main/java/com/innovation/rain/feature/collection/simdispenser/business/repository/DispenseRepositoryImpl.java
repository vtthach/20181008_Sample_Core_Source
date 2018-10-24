package com.innovation.rain.feature.collection.simdispenser.business.repository;



import com.innovation.rain.feature.collection.simdispenser.business.model.DispenseRequest;
import com.innovation.rain.feature.collection.simdispenser.business.model.DispenseResponse;
import com.innovation.rain.feature.collection.simdispenser.business.service.DispenseApiService;
import com.sf0404.core.application.business.BaseRepositoryImpl;

import javax.inject.Inject;

import io.reactivex.Observable;
import retrofit2.http.Body;

public class DispenseRepositoryImpl extends BaseRepositoryImpl<DispenseApiService> implements DispenseRepository {

    @Inject
    public DispenseRepositoryImpl(DispenseApiService service) {
        super(service);
    }

    @Override
    public Observable<DispenseResponse> dispense(@Body DispenseRequest request) {
        return service.dispense(request);
    }
}
