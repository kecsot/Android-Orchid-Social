package com.kecsot.orchidsocial.dagger.components

import com.kecsot.orchidsocial.MyApplication
import com.kecsot.orchidsocial.dagger.modules.AppModule
import com.kecsot.orchidsocial.dagger.modules.RepositoryModule
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {

    fun inject(app: MyApplication)

}