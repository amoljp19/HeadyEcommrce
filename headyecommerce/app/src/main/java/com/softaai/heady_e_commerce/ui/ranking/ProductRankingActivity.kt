package com.softaai.heady_e_commerce.ui.ranking

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.GridLayoutManager
import com.softaai.heady_e_commerce.R
import com.softaai.heady_e_commerce.BR
import com.softaai.heady_e_commerce.databinding.ActivityProductRankingBinding
import com.softaai.heady_e_commerce.di.component.DaggerProductRankingComponent
import com.softaai.heady_e_commerce.ui.base.BaseActivity
import com.softaai.heady_e_commerce.ui.main.adapter.ProductAdapter
import com.softaai.heady_e_commerce.utils.App
import com.softaai.heady_e_commerce.utils.RANKING_BY_ORDER
import com.softaai.heady_e_commerce.utils.RANKING_BY_VIEW
import javax.inject.Inject

/**
 * Created by Amol Pawar on 05-11-2018.
 * softAai Apps
 */
class ProductRankingActivity : BaseActivity<ActivityProductRankingBinding, ProductRankingViewModel>() {

    @Inject
    lateinit var mViewModelFactory: ViewModelProvider.Factory

    lateinit var mActivityBinding: ActivityProductRankingBinding

    @Inject
    lateinit var productDetailViewModel: ProductRankingViewModel

    @Inject
    lateinit var mLayoutManager: GridLayoutManager

    @Inject
    lateinit var productAdapter: ProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {

        DaggerProductRankingComponent
                .builder()
                .appComponent(App.instance.appComponent)
                .productRankingViewModule(ProductRankingViewModule())
                .build()
                .injectActivity(this)

        super.onCreate(savedInstanceState)

        mActivityBinding = viewDataBinding!!
        setup()

        if (intent != null) {
            var type = intent.getIntExtra("type", -1)
            if (type != -1) {
                supportActionBar!!.title = if (type == RANKING_BY_VIEW) getString(R.string.most_viewed)
                else if (type == RANKING_BY_ORDER) getString(R.string.most_ordered)
                else getString(R.string.most_shared)

                productDetailViewModel.getProductByRanking(type)
            }
        }

    }

    private fun setup() {
        mActivityBinding.productRecyclerView.setLayoutManager(mLayoutManager)
        mActivityBinding.productRecyclerView.setItemAnimator(DefaultItemAnimator())
        mActivityBinding.productRecyclerView.setAdapter(productAdapter)
    }

    override val viewModel: ProductRankingViewModel
        get() {
            productDetailViewModel = ViewModelProviders.of(this, mViewModelFactory).get(ProductRankingViewModel::class.java)
            return productDetailViewModel!!
        }

    override val bindingVariable: Int
        get() = BR.viewModel

    override val layoutId: Int
        get() = R.layout.activity_product_ranking

}
