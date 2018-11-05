package com.softaai.heady_e_commerce.utils

import android.databinding.BindingAdapter
import android.support.v7.widget.RecyclerView
import com.softaai.heady_e_commerce.data.model.local.Category
import com.softaai.heady_e_commerce.data.model.local.Product
import com.softaai.heady_e_commerce.ui.main.adapter.CategoryAdapter
import com.softaai.heady_e_commerce.ui.main.adapter.ProductAdapter
/**
 * Created by Amol Pawar on 05-11-2018.
 * softAai Apps
 */
@BindingAdapter("adapter")
fun addCategoryItems(recyclerView: RecyclerView,
                 categoryList: MutableList<Category>) {
    val adapter = recyclerView.adapter as? CategoryAdapter
    if (adapter != null) {
        adapter!!.clearItems()
        adapter!!.addItems(categoryList)
    }
}

@BindingAdapter("productAdapter")
fun addProductItems(recyclerView: RecyclerView,
                     productList: MutableList<Product>) {
    val adapter = recyclerView.adapter as? ProductAdapter
    if (adapter != null) {
        adapter!!.clearItems()
        adapter!!.addItems(productList)
    }
}

