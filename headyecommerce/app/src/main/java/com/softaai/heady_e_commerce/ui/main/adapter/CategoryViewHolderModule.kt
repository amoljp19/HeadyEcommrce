package com.softaai.heady_e_commerce.ui.main.adapter

import android.arch.lifecycle.ViewModelProvider
import android.content.Context
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import com.softaai.heady_e_commerce.data.database.DataManager
import com.softaai.heady_e_commerce.data.model.local.Product
import com.softaai.heady_e_commerce.di.ViewModelProviderFactory
import dagger.Module
import dagger.Provides

/**
 * Created by Amol Pawar on 05-11-2018.
 * softAai Apps
 */
@Module()
class CategoryViewHolderModule {

    @Provides
    fun provideCategoryViewModel(dataManager: DataManager) : CategoryViewModel {
        return CategoryViewModel(dataManager)
    }

    @Provides
    fun categoryViewModelProvider(categoryViewModel: CategoryViewModel): ViewModelProvider.Factory {
        return ViewModelProviderFactory(categoryViewModel)
    }

    @Provides
    fun provideLinearLayoutManager(context : Context): LinearLayoutManager {
        return LinearLayoutManager(context)
    }

    @Provides
    fun provideGridLayoutManager(context: Context): GridLayoutManager {
        return GridLayoutManager(context, 3)
    }

    @Provides
    fun provideProductAdapter(): ProductAdapter {
        return ProductAdapter(ArrayList<Product>())
    }

}