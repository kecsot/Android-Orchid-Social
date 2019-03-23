package com.kecsot.orchidsocial.dagger.components

import com.kecsot.orchidsocial.dagger.modules.ManagerModule
import com.kecsot.orchidsocial.screens.login.LoginActivity
import dagger.Component
import javax.inject.Singleton

/**
 * Created by kecsotamas on 2018. 03. 10..
 */
@Singleton
@Component(modules = arrayOf(ManagerModule::class))
interface ManagerComponent {

    fun inject(activity: LoginActivity)

}