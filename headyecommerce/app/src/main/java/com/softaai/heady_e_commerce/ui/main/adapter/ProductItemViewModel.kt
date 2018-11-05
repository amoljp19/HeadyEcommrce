package com.softaai.heady_e_commerce.ui.main.adapter

import android.databinding.ObservableField
import com.softaai.heady_e_commerce.data.model.local.Product

/**
 * Created by Amol Pawar on 05-11-2018.
 * softAai Apps
 */
class ProductItemViewModel  {

    var productImg : ObservableField<String>
    var productName : ObservableField<String>
    var productId : ObservableField<Int>
    var categoryId : ObservableField<Int>

    private var product : Product
    private var productViewModelListener: ProductViewModelListener

    constructor(product: Product, viewModelListener: ProductViewModelListener) {
        this.product = product
        this.productViewModelListener = viewModelListener
        productImg = ObservableField<String>(product.productName)
        productName = ObservableField<String>(product.productName)
        productId = ObservableField<Int>(product.productId)
        categoryId = ObservableField<Int>(product.categoryId)
    }

    interface ProductViewModelListener {
        fun openProductDetailClick(product: Product)
    }

    fun openProductDetail() {
        productViewModelListener.openProductDetailClick(product)
    }

}