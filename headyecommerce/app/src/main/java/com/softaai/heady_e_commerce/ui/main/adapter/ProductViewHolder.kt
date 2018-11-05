package com.softaai.heady_e_commerce.ui.main.adapter

import android.content.Intent
import android.support.v7.widget.RecyclerView
import com.softaai.heady_e_commerce.data.model.local.Product
import com.softaai.heady_e_commerce.databinding.ItemProductBinding
import com.softaai.heady_e_commerce.ui.productDetail.ProductDetailActivity

/**
 * Created by Amol Pawar on 05-11-2018.
 * softAai Apps
 */
class ProductViewHolder(productBinding: ItemProductBinding) : RecyclerView.ViewHolder(productBinding.root), ProductItemViewModel.ProductViewModelListener {


    private val itemProductBinding = productBinding

    private lateinit var bindingViewModel : ProductItemViewModel

    var productViewModelListener: ProductItemViewModel.ProductViewModelListener = this

    fun bind(product: Product) = with(itemView) {
        bindingViewModel = ProductItemViewModel(product, productViewModelListener)
        itemProductBinding.viewModel = bindingViewModel
        itemProductBinding.executePendingBindings()
    }

    override fun openProductDetailClick(product: Product) {
        var intent = Intent(itemProductBinding.root.context, ProductDetailActivity::class.java)
        intent.putExtra("product", product)
        itemProductBinding.root.context.startActivity(intent)
    }
}
