package com.softaai.heady_e_commerce.ui.productDetail

import android.arch.lifecycle.ViewModelProvider
import android.content.Context
import android.support.v7.widget.LinearLayoutManager
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
@Module()
class ProductDetailViewModule {

    @Provides
    fun provideProductDetailViewModel(dataManager: DataManager) : ProductDetailViewModel {
        return ProductDetailViewModel(dataManager)
    }

    @Provides
    fun categoryViewModelProvider(productDetailViewModel: ProductDetailViewModel): ViewModelProvider.Factory {
        return ViewModelProviderFactory(productDetailViewModel)
    }

    @Provides
    fun provideLinearLayoutManager(context : Context): LinearLayoutManager {
        return LinearLayoutManager(context)
    }

    @Provides
    fun provideProductAdapter(): ProductAdapter {
        return ProductAdapter(ArrayList<Product>())
    }

}