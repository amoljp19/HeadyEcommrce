package com.softaai.heady_e_commerce.di.component

import com.softaai.heady_e_commerce.di.annotations.AppScope
import com.softaai.heady_e_commerce.ui.main.MainActivity
import com.softaai.heady_e_commerce.ui.main.MainActivityModule
import dagger.Component


/**
 * Created by Amol Pawar on 05-11-2018.
 * softAai Apps
 */

@AppScope
@Component(dependencies = arrayOf(AppComponent::class), modules = arrayOf(MainActivityModule::class))
interface MainActivityComponent : AppComponent {

    fun injectMainActivity(mainActivity: MainActivity)

}