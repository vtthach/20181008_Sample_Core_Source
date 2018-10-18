package com.innovation.rain.feature.collection.signin.injection;

import com.amb.retrofitwrapper.RetrofitConstants;
import com.innovation.rain.feature.collection.signin.business.mapper.ClientSignInMapper;
import com.innovation.rain.feature.collection.signin.business.mapper.ClientSignInMapperImpl;
import com.innovation.rain.feature.collection.signin.business.repository.ClientSignInRepository;
import com.innovation.rain.feature.collection.signin.business.repository.ClientSignInRepositoryImpl;
import com.innovation.rain.feature.collection.signin.business.service.ClientSignInApiService;
import com.innovation.rain.feature.collection.signin.business.usecase.ClientSignInUseCase;
import com.innovation.rain.feature.collection.signin.business.usecase.ClientSignInUseCaseImpl;
import com.sf0404.core.application.scope.PerView;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class ClientSignInUseCaseModule {

    @Provides
    @PerView
    public ClientSignInRepository provideRepository(ClientSignInRepositoryImpl repository) {
        return repository;
    }

    @PerView
    @Provides
    public ClientSignInApiService provideApiService(@Named(RetrofitConstants.RETROFIT_SELECTED) Retrofit retrofit) {
        return retrofit.create(ClientSignInApiService.class);
    }

    @PerView
    @Provides
    public ClientSignInUseCase provideUseCase(ClientSignInUseCaseImpl useCase) {
        return useCase;
    }

    @PerView
    @Provides
    public ClientSignInMapper provideMapper(ClientSignInMapperImpl useCase) {
        return useCase;
    }
}
