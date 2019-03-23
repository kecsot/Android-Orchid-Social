package com.kecsot.orchidsocial.dagger.modules

import com.kecsot.orchidsocial.rest.AuthRemoteInterface
import com.kecsot.orchidsocial.rest.PostRemoteInterface
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
class RemoteServiceModule(val apiClient: Retrofit) {

    @Provides
    @Singleton
    fun providePostInterface(): PostRemoteInterface = apiClient.create(PostRemoteInterface::class.java)

    @Provides
    @Singleton
    fun provideAuthRemoteInterface(): AuthRemoteInterface = apiClient.create(AuthRemoteInterface::class.java)

}