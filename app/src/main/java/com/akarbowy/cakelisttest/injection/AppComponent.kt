package com.akarbowy.cakelisttest.injection

import android.content.Context
import com.akarbowy.cakelisttest.data.injection.DataModule
import com.akarbowy.cakelisttest.data.injection.NetworkModule
import com.akarbowy.cakelisttest.ui.MainViewModel
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        DataModule::class,
        NetworkModule::class
    ]
)
internal interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun applicationContext(applicationContext: Context): Builder

        fun build(): AppComponent
    }

    val mainViewModel: MainViewModel

}