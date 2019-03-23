package com.kecsot.orchidsocial.dagger.modules

import com.kecsot.orchidsocial.MyApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class AppModule(val app: MyApplication) {
    @Provides
    @Singleton
    fun provideApp() = app
}