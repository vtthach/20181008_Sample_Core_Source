package com.innovation.rain.feature.agentlogout.injection

import com.amb.retrofitwrapper.RetrofitConstants
import com.innovation.rain.feature.agentlogout.business.mapper.AgentLogoutMapper
import com.innovation.rain.feature.agentlogout.business.mapper.AgentLogoutMapperImpl
import com.innovation.rain.feature.agentlogout.business.repository.AgentLogoutRepository
import com.innovation.rain.feature.agentlogout.business.repository.AgentLogoutRepositoryImpl
import com.innovation.rain.feature.agentlogout.business.service.AgentLogoutService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Named

@Module
class AgentLogoutRepoModule {
    @Provides
    fun provideService(@Named(RetrofitConstants.RETROFIT_SELECTED) retrofit: Retrofit): AgentLogoutService {
        return retrofit.create(AgentLogoutService::class.java)
    }

    @Provides
    fun provideRepository(service: AgentLogoutService, mapper: AgentLogoutMapper): AgentLogoutRepository {
        return AgentLogoutRepositoryImpl(service, mapper)
    }

    @Provides
    fun provideMapper(): AgentLogoutMapper {
        return AgentLogoutMapperImpl()
    }
}