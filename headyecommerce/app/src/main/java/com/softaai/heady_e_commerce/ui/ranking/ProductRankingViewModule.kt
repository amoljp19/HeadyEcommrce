package com.softaai.heady_e_commerce.ui.ranking

import android.arch.lifecycle.ViewModelProvider
import android.content.Context
import android.support.v7.widget.GridLayoutManager
import com.softaai.heady_e_commerce.data.database.DataManager
import com.softaai.heady_e_commerce.data.model.local.Product
import com.softaai.heady_e_commerce.di.ViewModelProviderFactory
import com.softaai.heady_e_commerce.ui.main.adapter.ProductAdapter

import dagger.Module
import dagger.Provides
/**
 * Created by Amol Pawar on 05-11-2018.
 * softAai Apps
 */
@Module
class ProductRankingViewModule {

    @Provides
    fun provideProductRankingViewModel(dataManager: DataManager) : ProductRankingViewModel {
        return ProductRankingViewModel(dataManager)
    }

    @Provides
    fun categoryViewModelProvider(productRankingViewModel: ProductRankingViewModel): ViewModelProvider.Factory {
        return ViewModelProviderFactory(productRankingViewModel)
    }

    @Provides
    fun provideGridLayoutManager(context : Context): GridLayoutManager {
        return GridLayoutManager(context, 3)
    }

    @Provides
    fun provideProductAdapter(): ProductAdapter {
        return ProductAdapter(ArrayList<Product>())
    }
}