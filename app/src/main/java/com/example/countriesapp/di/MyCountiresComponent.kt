package com.example.countriesapp.di

import android.app.Application
import com.example.countriesapp.ui.detail.DetailActivityComponent
import com.example.countriesapp.ui.detail.DetailActivityModule
import com.example.countriesapp.ui.main.MainActivityComponent
import com.example.countriesapp.ui.main.MainActivityModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, DataModule::class])
interface MyCountiresComponent {

    fun plus(module: MainActivityModule): MainActivityComponent
    fun plus(module: DetailActivityModule): DetailActivityComponent

    @Component.Factory
    interface Factory{
        fun create(@BindsInstance app: Application) : MyCountiresComponent
    }
}