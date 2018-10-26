package com.innovation.rain.feature.collection.simdispenser.business.usecase;

import com.innovation.rain.feature.collection.simdispenser.business.mapper.DispenseMapper;
import com.innovation.rain.feature.collection.simdispenser.business.model.DispenseParam;
import com.innovation.rain.feature.collection.simdispenser.business.model.DispenseRequest;
import com.innovation.rain.feature.collection.simdispenser.business.model.DispenseResponse;
import com.innovation.rain.feature.collection.simdispenser.business.model.DispenseUiModel;
import com.innovation.rain.feature.collection.simdispenser.business.model.SimDispenseCallback;
import com.innovation.rain.feature.collection.simdispenser.business.repository.DispenseRepository;
import com.sf0404.core.application.business.core.exception.BusinessErrorException;
import com.sf0404.core.application.business.usecase.UseCaseImpl;

import javax.inject.Inject;

import io.reactivex.Observable;

public class DispenseUseCaseImpl extends UseCaseImpl<DispenseUiModel, DispenseParam, DispenseRequest, DispenseResponse, SimDispenseCallback>
        implements DispenseUseCase {

    private final DispenseRepository repository;

    @Inject
    public DispenseUseCaseImpl(DispenseMapper mapper, DispenseRepository repository) {
        super(mapper);
        this.repository = repository;
    }

    @Override
    protected Observable<DispenseResponse> getRepositoryObservable(DispenseRequest requestFromParam) {
        return repository.dispense(requestFromParam);
    }

    @Override
    protected boolean handleBusiness(BusinessErrorException e) {
        return false;
    }
}
