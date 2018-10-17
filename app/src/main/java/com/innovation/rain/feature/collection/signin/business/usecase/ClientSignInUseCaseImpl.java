package com.innovation.rain.feature.collection.signin.business.usecase;

import com.innovation.rain.feature.collection.signin.business.mapper.ClientSignInMapper;
import com.innovation.rain.feature.collection.signin.business.model.ClientSignInCallback;
import com.innovation.rain.feature.collection.signin.business.model.ClientSignInParam;
import com.innovation.rain.feature.collection.signin.business.model.ClientSignInRequest;
import com.innovation.rain.feature.collection.signin.business.model.ClientSignInResponse;
import com.innovation.rain.feature.collection.signin.business.model.ClientSignInUiModel;
import com.innovation.rain.feature.collection.signin.business.repository.ClientSignInRepository;
import com.sf0404.core.application.business.core.exception.BusinessErrorException;
import com.sf0404.core.application.business.usecase.UseCaseImpl;

import javax.inject.Inject;

import io.reactivex.Observable;

public class ClientSignInUseCaseImpl extends UseCaseImpl<ClientSignInUiModel, ClientSignInParam, ClientSignInRequest, ClientSignInResponse, ClientSignInCallback>
        implements ClientSignInUseCase {

    private final ClientSignInRepository repository;

    @Inject
    public ClientSignInUseCaseImpl(ClientSignInMapper mapper, ClientSignInRepository repository) {
        super(mapper);
        this.repository = repository;
    }

    @Override
    protected Observable<ClientSignInResponse> getRepositoryObservable(ClientSignInRequest requestFromParam) {
        return repository.post(requestFromParam);
    }

    @Override
    protected boolean handleBusiness(BusinessErrorException e) {
        return isNoOrderException(e);
    }

    @Override
    protected void onUnknownError(Throwable e) { // TODO remove this method when integrate real API caused only hard code to test onNoOrder
        callback.onNoOrder(e.getMessage());
    }

    private boolean isNoOrderException(BusinessErrorException e) {
        callback.onNoOrder(e.getCode());
        return true; // TODO update when have real API
    }
}
