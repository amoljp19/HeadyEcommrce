package com.softaai.heady_e_commerce.utils

import android.app.Application
import com.softaai.heady_e_commerce.di.component.AppComponent
import com.softaai.heady_e_commerce.di.component.DaggerAppComponent


/**
 * Created by Amol Pawar on 05-11-2018.
 * softAai Apps
 */

class App : Application() {

    lateinit var appComponent : AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this

        appComponent = DaggerAppComponent.builder()
                .application(this)
                .build()

    }


    companion object {
        lateinit var instance: App
            private set
    }
}