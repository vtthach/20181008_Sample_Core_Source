package com.innovation.rain.feature.collection.simdispenser.injection;

import android.app.Activity;
import android.content.Context;

import com.amb.retrofitwrapper.RetrofitConstants;
import com.innovation.rain.feature.collection.signin.view.SimDispenserFragment;
import com.innovation.rain.feature.collection.simdispenser.business.mapper.DispenseMapper;
import com.innovation.rain.feature.collection.simdispenser.business.mapper.DispenseMapperImpl;
import com.innovation.rain.feature.collection.simdispenser.business.repository.DispenseRepository;
import com.innovation.rain.feature.collection.simdispenser.business.repository.DispenseRepositoryImpl;
import com.innovation.rain.feature.collection.simdispenser.business.service.DispenseApiService;
import com.innovation.rain.feature.collection.simdispenser.business.usecase.DispenseUseCase;
import com.innovation.rain.feature.collection.simdispenser.business.usecase.DispenseUseCaseImpl;
import com.rain.carddispenser.BarcodeScanner;
import com.rain.carddispenser.BarcodeScannerImpl;
import com.rain.carddispenser.CardDispenser;
import com.rain.carddispenser.CardDispenserController;
import com.rain.carddispenser.CardDispenserControllerImpl;
import com.rain.carddispenser.CardDispenserImpl;
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

    @PerView
    @Provides
    public CardDispenserController provideCardDispenserController(CardDispenser cardDispenser, BarcodeScanner barcodeScanner) {
        return new CardDispenserControllerImpl(cardDispenser, barcodeScanner);
    }

    @PerView
    @Provides
    public CardDispenser provideCardDispenser(Context context) {
        return new CardDispenserImpl(context);
    }

    @PerView
    @Provides
    public BarcodeScanner provideBarcodeScanner(CardDispenser cardDispenser, Activity activity) {
        return new BarcodeScannerImpl(cardDispenser, activity);
    }

    @PerView
    @Provides
    public Activity provideActivityr(SimDispenserFragment fragment) {
        return fragment.getActivity();
    }
}
