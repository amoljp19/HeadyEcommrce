package com.softaai.heady_e_commerce.ui.main

import android.arch.lifecycle.ViewModelProvider
import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import com.softaai.heady_e_commerce.data.database.DataManager
import com.softaai.heady_e_commerce.data.model.local.Category
import com.softaai.heady_e_commerce.di.ViewModelProviderFactory
import com.softaai.heady_e_commerce.ui.main.adapter.CategoryAdapter
import dagger.Module
import dagger.Provides
import java.util.ArrayList

/**
 * Created by Amol Pawar on 05-11-2018.
 * softAai Apps
 */
@Module()
class MainActivityModule {

    @Provides
    fun provideMainViewModel(dataManager: DataManager): MainViewModel {
        return MainViewModel(dataManager)
    }

    @Provides
    fun mainViewModelProvider(mainViewModel: MainViewModel): ViewModelProvider.Factory {
        return ViewModelProviderFactory(mainViewModel)
    }

    @Provides
    fun provideLinearLayoutManager(context: Context): LinearLayoutManager {
        return LinearLayoutManager(context)
    }

    @Provides
    fun provideCategoryAdapter(): CategoryAdapter {
        return CategoryAdapter(ArrayList<Category>())
    }

}