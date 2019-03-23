package com.kecsot.orchidsocial

import android.app.Application
import com.crashlytics.android.Crashlytics
import com.kecsot.orchidsocial.dagger.components.*
import com.kecsot.orchidsocial.dagger.modules.AppModule
import com.kecsot.orchidsocial.dagger.modules.RemoteServiceModule
import com.kecsot.orchidsocial.rest.retrofit.ApiClient
import io.fabric.sdk.android.Fabric


class MyApplication : Application() {

    companion object {
        lateinit var instance: MyApplication
    }

    init {
        instance = this
    }

    val component: AppComponent by lazy {
        DaggerAppComponent
                .builder()
                .appModule(AppModule(this))
                .build()
    }

    val repositoryComponent: RepositoryComponent by lazy {
        DaggerRepositoryComponent
                .builder()
                .build();
    }

    val managerComponent: ManagerComponent by lazy {
        DaggerManagerComponent
                .builder()
                .build();
    }

    val remoteServiceComponent: RemoteServiceComponent by lazy {
        val apiClient = ApiClient().getClient( "http://63f83aeb.ngrok.io/tolemneked-server/public/")
        val module = RemoteServiceModule(apiClient)

        DaggerRemoteServiceComponent
                .builder()
                .remoteServiceModule(module)
                .build();
    }

    override fun onCreate() {
        super.onCreate()

        Fabric.with(this, Crashlytics())
        component.inject(this)
    }

}

