package com.softaai.heady_e_commerce.base

import android.arch.lifecycle.ViewModel
import com.softaai.heady_e_commerce.di.component.DaggerViewModelInjector
import com.softaai.heady_e_commerce.di.component.ViewModelInjector
import com.softaai.heady_e_commerce.di.module.NetworkModule
import com.softaai.heady_e_commerce.ui.CategoryListViewModel


/**
 * Created by Amol Pawar on 02-11-2018.
 * softAai Apps
 */

abstract class BaseViewModel: ViewModel(){

    private val injector: ViewModelInjector = DaggerViewModelInjector
            .builder()
            .networkModule(NetworkModule)
            .build()

    init {
        inject()
    }


    private fun inject() {
        when (this) {
            is CategoryListViewModel -> injector.inject(this)
        }
    }

}