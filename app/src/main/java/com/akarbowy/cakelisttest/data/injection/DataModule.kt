package com.akarbowy.cakelisttest.data.injection

import com.akarbowy.cakelisttest.data.CakesRepository
import com.akarbowy.cakelisttest.data.CakesRepositoryImpl
import com.akarbowy.cakelisttest.data.remote.WaracleCakeApi
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
internal object DataModule {

    @JvmStatic
    @Provides
    @Singleton
    fun provideCakesRepository(api: WaracleCakeApi): CakesRepository = CakesRepositoryImpl(api)
}