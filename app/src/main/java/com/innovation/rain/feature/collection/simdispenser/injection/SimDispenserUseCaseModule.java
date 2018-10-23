package com.innovation.rain.feature.collection.simdispenser.injection;

import com.amb.retrofitwrapper.RetrofitConstants;
import com.innovation.rain.feature.collection.simdispenser.business.mapper.DispenseMapper;
import com.innovation.rain.feature.collection.simdispenser.business.mapper.DispenseMapperImpl;
import com.innovation.rain.feature.collection.simdispenser.business.repository.DispenseRepository;
import com.innovation.rain.feature.collection.simdispenser.business.repository.DispenseRepositoryImpl;
import com.innovation.rain.feature.collection.simdispenser.business.service.DispenseApiService;
import com.innovation.rain.feature.collection.simdispenser.business.usecase.DispenseUseCase;
import com.innovation.rain.feature.collection.simdispenser.business.usecase.DispenseUseCaseImpl;
import com.sf0404.core.application.scope.PerView;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class SimDispenserUseCaseModule {

    @Provides
    @PerView
    public DispenseRepository provideRepository(DispenseRepositoryImpl repository) {
        return repository;
    }

    @PerView
    @Provides
    public DispenseApiService provideApiService(@Named(RetrofitConstants.RETROFIT_SELECTED) Retrofit retrofit) {
        return retrofit.create(DispenseApiService.class);
    }

    @PerView
    @Provides
    public DispenseUseCase provideUseCase(DispenseUseCaseImpl useCase) {
        return useCase;
    }

    @PerView
    @Provides
    public DispenseMapper provideMapper(DispenseMapperImpl useCase) {
        return useCase;
    }
}
