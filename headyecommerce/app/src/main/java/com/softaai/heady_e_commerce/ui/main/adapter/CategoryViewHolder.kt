package com.softaai.heady_e_commerce.ui.main.adapter

import android.content.Intent
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import com.softaai.heady_e_commerce.data.model.local.Category
import com.softaai.heady_e_commerce.databinding.ItemCategoryBinding
import com.softaai.heady_e_commerce.di.component.DaggerViewHolderComponent
import com.softaai.heady_e_commerce.ui.base.BaseViewHolder
import com.softaai.heady_e_commerce.ui.products.ProductsActivity
import com.softaai.heady_e_commerce.utils.App
import javax.inject.Inject
/**
 * Created by Amol Pawar on 05-11-2018.
 * softAai Apps
 */
class CategoryViewHolder (categoryBinding: ItemCategoryBinding) : BaseViewHolder<ItemCategoryBinding, CategoryViewModel>(categoryBinding.root), CategoryViewModel.CategoryViewModelListener {

    private val itemCategoryBinding = categoryBinding

    private lateinit var bindingViewModel : CategoryViewModel

    @Inject
    lateinit var layoutManager : LinearLayoutManager

    @Inject
    lateinit var productAdapter : ProductAdapter

    @Inject
    lateinit var categoryViewModel: CategoryViewModel

    private var viewHolder: CategoryViewHolder


    init {

        viewHolder = this

        DaggerViewHolderComponent.builder()
                .appComponent(App.instance.appComponent)
                .categoryViewHolderModule(CategoryViewHolderModule())
                .build()
                .inject(this)
    }


    fun bind(category: Category) = with(itemView) {
        bindingViewModel = categoryViewModel!!
        itemCategoryBinding.viewModel = bindingViewModel

        bindingViewModel.setCategory(category)
        bindingViewModel.categoryViewListener = viewHolder

        layoutManager!!.orientation = LinearLayoutManager.HORIZONTAL
        itemCategoryBinding.recyclerProduct.setLayoutManager(layoutManager)
        itemCategoryBinding.recyclerProduct.setItemAnimator(DefaultItemAnimator())
        itemCategoryBinding.recyclerProduct.setAdapter(productAdapter)

        itemCategoryBinding.executePendingBindings()
    }

    override fun onMoreOptionClick(category: Category) {
        var intent = Intent(itemCategoryBinding.root.context, ProductsActivity::class.java)
        intent.putExtra("category", category)
        itemCategoryBinding.root.context.startActivity(intent)
    }

}
