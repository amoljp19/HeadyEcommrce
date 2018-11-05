package com.softaai.heady_e_commerce.di.component

import android.app.Application
import android.content.Context
import com.softaai.heady_e_commerce.data.database.DataManager
import com.softaai.heady_e_commerce.di.module.AppModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton


/**
 * Created by Amol Pawar on 05-11-2018.
 * softAai Apps
 */

@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent

    }

    fun getContext() : Context

    fun getDataManager() : DataManager

}