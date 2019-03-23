package com.kecsot.orchidsocial.dagger.modules

import com.kecsot.orchidsocial.repositories.AuthRepository
import com.kecsot.orchidsocial.repositories.ImageRepository
import com.kecsot.orchidsocial.repositories.PostRepository
import com.kecsot.orchidsocial.repositories.UserRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideTestRepository(): PostRepository = PostRepository()

    @Provides
    @Singleton
    fun provideImageRepository(): ImageRepository = ImageRepository()

    @Provides
    @Singleton
    fun provideUserRepository(): UserRepository = UserRepository()

    @Provides
    @Singleton
    fun provideAuthRepository(): AuthRepository = AuthRepository()
}