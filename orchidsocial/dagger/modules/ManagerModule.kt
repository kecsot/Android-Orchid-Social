package com.kecsot.orchidsocial.dagger.modules

import com.kecsot.orchidsocial.screens.login.OrchidLoginManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class ManagerModule() {

    @Provides
    @Singleton
    fun provideLoginManager(): OrchidLoginManager = OrchidLoginManager()

}