package com.example.stravaapi.di

import android.content.Context
import com.example.stravaapi.ui.login.LoginActivity
import com.example.stravaapi.ui.main.MainActivity
import com.example.stravaapi.utils.lifecycle.ViewModelTracker
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        ViewModelModule::class,
    ]
)
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun context(context: Context)
        fun build(): AppComponent
    }

    fun provideViewModelTracker(): ViewModelTracker

    fun inject(entity: LoginActivity)
    fun inject(entity: MainActivity)

}