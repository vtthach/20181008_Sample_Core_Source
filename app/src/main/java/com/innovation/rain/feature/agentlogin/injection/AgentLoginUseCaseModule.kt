package com.innovation.rain.feature.agentlogin.injection

import com.amb.retrofitwrapper.RetrofitConstants
import com.innovation.rain.feature.agentlogin.business.mapper.AgentLoginMapper
import com.innovation.rain.feature.agentlogin.business.mapper.AgentLoginMapperImpl
import com.innovation.rain.feature.agentlogin.business.repository.AgentLoginRepository
import com.innovation.rain.feature.agentlogin.business.repository.AgentLoginRepositoryImpl
import com.innovation.rain.feature.agentlogin.business.service.AgentLoginService
import com.innovation.rain.feature.agentlogin.business.usecase.AgentLoginUseCase
import com.innovation.rain.feature.agentlogin.business.usecase.AgentLoginUseCaseImpl
import com.sf0404.core.application.scope.PerView
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Named

@Module
class AgentLoginUseCaseModule {
    @PerView
    @Provides
    fun provideService(@Named(RetrofitConstants.RETROFIT_SELECTED) retrofit: Retrofit): AgentLoginService {
        return retrofit.create(AgentLoginService::class.java)
    }

    @PerView
    @Provides
    fun provideRepository(service: AgentLoginService): AgentLoginRepository {
        return AgentLoginRepositoryImpl(service)
    }

    @PerView
    @Provides
    fun provideMapper(): AgentLoginMapper {
        return AgentLoginMapperImpl()
    }

    @PerView
    @Provides
    fun provideUseCase(repository: AgentLoginRepository, mapper: AgentLoginMapper): AgentLoginUseCase {
        return AgentLoginUseCaseImpl(repository, mapper)
    }
}