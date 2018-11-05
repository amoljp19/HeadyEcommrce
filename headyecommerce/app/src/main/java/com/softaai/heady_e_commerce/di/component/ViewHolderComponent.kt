package com.softaai.heady_e_commerce.di.component

import com.softaai.heady_e_commerce.di.annotations.AppScope
import com.softaai.heady_e_commerce.ui.main.adapter.CategoryViewHolder
import com.softaai.heady_e_commerce.ui.main.adapter.CategoryViewHolderModule
import com.softaai.heady_e_commerce.ui.products.ProductsActivity
import dagger.Component


/**
 * Created by Amol Pawar on 05-11-2018.
 * softAai Apps
 */

@AppScope
@Component(dependencies = arrayOf(AppComponent::class), modules = arrayOf(CategoryViewHolderModule::class))
interface ViewHolderComponent : AppComponent {

    fun inject(viewHolder: CategoryViewHolder)

    fun injectProductsActivity(productsActivity: ProductsActivity)

}