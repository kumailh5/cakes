package com.kumail.cakes.di

import com.kumail.cakes.data.repository.CakesRepository
import com.kumail.cakes.data.repository.CakesRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

/**
 * Created by kumailhussain on 16/11/2021.
 */
@Module
@InstallIn(ViewModelComponent::class)
interface RepositoryModule {

    @Binds
    fun provideCakesRepository(cakesRepositoryImpl: CakesRepositoryImpl): CakesRepository
}