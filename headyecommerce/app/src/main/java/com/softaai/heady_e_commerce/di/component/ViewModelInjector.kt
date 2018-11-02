package com.softaai.heady_e_commerce.di.component

import com.softaai.heady_e_commerce.di.module.NetworkModule
import com.softaai.heady_e_commerce.ui.CategoryListViewModel
import dagger.Component
import javax.inject.Singleton


/**
 * Created by Amol Pawar on 02-11-2018.
 * softAai Apps
 */

@Singleton
@Component(modules = [(NetworkModule::class)])
interface ViewModelInjector {

    fun inject(categoryListViewModel: CategoryListViewModel)

    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector

        fun networkModule(networkModule: NetworkModule): Builder
    }
}