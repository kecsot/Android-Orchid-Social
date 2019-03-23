package com.kecsot.orchidsocial.dagger.components

import com.kecsot.orchidsocial.dagger.modules.RemoteServiceModule
import com.kecsot.orchidsocial.repositories.AuthRepository
import com.kecsot.orchidsocial.repositories.PostRepository
import dagger.Component
import javax.inject.Singleton

/**
 * Created by kecsotamas on 2018. 03. 10..
 */
@Singleton
@Component(modules = arrayOf(RemoteServiceModule::class))
interface RemoteServiceComponent {

    fun inject(activity: PostRepository)

    fun inject(activity: AuthRepository)

}