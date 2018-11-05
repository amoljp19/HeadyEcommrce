package com.softaai.heady_e_commerce.di.component

import com.softaai.heady_e_commerce.di.annotations.AppScope
import com.softaai.heady_e_commerce.ui.ranking.ProductRankingActivity
import com.softaai.heady_e_commerce.ui.ranking.ProductRankingViewModule
import dagger.Component


/**
 * Created by Amol Pawar on 05-11-2018.
 * softAai Apps
 */

@AppScope
@Component(dependencies = arrayOf(AppComponent::class), modules = arrayOf(ProductRankingViewModule::class))
interface ProductRankingComponent : AppComponent {

    fun injectActivity(productRankingActivity: ProductRankingActivity)

}