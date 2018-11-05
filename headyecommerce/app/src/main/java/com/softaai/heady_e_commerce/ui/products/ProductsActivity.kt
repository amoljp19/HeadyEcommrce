package com.softaai.heady_e_commerce.ui.products

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.GridLayoutManager
import com.softaai.heady_e_commerce.R
import com.softaai.heady_e_commerce.BR
import com.softaai.heady_e_commerce.data.model.local.Category
import com.softaai.heady_e_commerce.databinding.ActivityProductsBinding
import com.softaai.heady_e_commerce.di.component.DaggerViewHolderComponent
import com.softaai.heady_e_commerce.ui.base.BaseActivity
import com.softaai.heady_e_commerce.ui.main.adapter.CategoryViewHolderModule
import com.softaai.heady_e_commerce.ui.main.adapter.CategoryViewModel
import com.softaai.heady_e_commerce.ui.main.adapter.ProductAdapter
import com.softaai.heady_e_commerce.utils.App
import javax.inject.Inject

/**
 * Created by Amol Pawar on 05-11-2018.
 * softAai Apps
 */
class ProductsActivity : BaseActivity<ActivityProductsBinding, CategoryViewModel>()  {

    @Inject
    lateinit var mViewModelFactory: ViewModelProvider.Factory

    lateinit var mActivityBinding: ActivityProductsBinding

    @Inject
    lateinit var productViewModel: CategoryViewModel

    @Inject
    lateinit var mLayoutManager: GridLayoutManager

    @Inject
    lateinit var productAdapter: ProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {

        DaggerViewHolderComponent.builder()
                .appComponent(App.instance.appComponent)
                .categoryViewHolderModule(CategoryViewHolderModule())
                .build()
                .injectProductsActivity(this)


        super.onCreate(savedInstanceState)

        mActivityBinding = viewDataBinding!!
        setup()

        if (intent != null) {
            var category = intent.getParcelableExtra<Category>("category")
            supportActionBar!!.title = category.categoryName
            productViewModel.setCategory(category)
        }
    }

    private fun setup() {
        mActivityBinding.recyclerProductGrid.setLayoutManager(mLayoutManager)
        mActivityBinding.recyclerProductGrid.setItemAnimator(DefaultItemAnimator())
        mActivityBinding.recyclerProductGrid.setAdapter(productAdapter)
    }

    override val viewModel : CategoryViewModel
        get() {
            productViewModel = ViewModelProviders.of(this, mViewModelFactory).get(CategoryViewModel::class.java)
            return productViewModel!!
        }

    override val bindingVariable: Int
        get() = BR.viewModel

    override val layoutId: Int
        get() = R.layout.activity_products


}
