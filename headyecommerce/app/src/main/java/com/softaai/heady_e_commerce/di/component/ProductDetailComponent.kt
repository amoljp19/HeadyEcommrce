package com.softaai.heady_e_commerce.di.component

import com.softaai.heady_e_commerce.di.annotations.AppScope
import com.softaai.heady_e_commerce.ui.productDetail.ProductDetailActivity
import com.softaai.heady_e_commerce.ui.productDetail.ProductDetailViewModule
import dagger.Component


/**
 * Created by Amol Pawar on 05-11-2018.
 * softAai Apps
 */

@AppScope
@Component(dependencies = arrayOf(AppComponent::class), modules = arrayOf(ProductDetailViewModule::class))
interface ProductDetailComponent : AppComponent {

    fun injectProductDetailActivity(productDetailActivity: ProductDetailActivity)

}