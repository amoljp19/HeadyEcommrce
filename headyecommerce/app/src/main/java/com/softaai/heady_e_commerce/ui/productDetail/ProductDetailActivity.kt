package com.softaai.heady_e_commerce.ui.productDetail

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.text.TextUtils
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.softaai.heady_e_commerce.R
import com.softaai.heady_e_commerce.BR
import com.softaai.heady_e_commerce.data.model.local.Product
import com.softaai.heady_e_commerce.databinding.ActivityDetailBinding
import com.softaai.heady_e_commerce.di.component.DaggerProductDetailComponent
import com.softaai.heady_e_commerce.ui.base.BaseActivity
import com.softaai.heady_e_commerce.ui.main.adapter.ProductAdapter
import com.softaai.heady_e_commerce.utils.App
import com.softaai.heady_e_commerce.utils.Util
import javax.inject.Inject

/**
 * Created by Amol Pawar on 05-11-2018.
 * softAai Apps
 */

class ProductDetailActivity : BaseActivity<ActivityDetailBinding, ProductDetailViewModel>() {

    @Inject
    lateinit var mViewModelFactory: ViewModelProvider.Factory

    lateinit var mActivityBinding: ActivityDetailBinding

    @Inject
    lateinit var productDetailViewModel: ProductDetailViewModel

    @Inject
    lateinit var mLayoutManager: LinearLayoutManager

    @Inject
    lateinit var productAdapter: ProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {

        DaggerProductDetailComponent.builder()
                .appComponent(App.instance.appComponent)
                .productDetailViewModule(ProductDetailViewModule())
                .build()
                .injectProductDetailActivity(this)


        super.onCreate(savedInstanceState)

        mActivityBinding = viewDataBinding!!

        setup()

        if (intent != null) {
            var product = intent.getParcelableExtra<Product>("product")

            supportActionBar!!.title = product.productName
            setColorAndSize(product)
            productDetailViewModel.setProduct(product)
        }
    }

    private fun setColorAndSize(product: Product) {
        var layoutParams = LinearLayout.LayoutParams(100, 100)
        layoutParams.rightMargin = 20
        layoutParams.gravity = Gravity.CENTER

        var colorMap = HashMap<String, Int>()
        var sizeMap = HashMap<String, Int>()
        for (variant in product.variantInfo!!) {

            if (!colorMap.containsKey(variant.color)) {
                colorMap.put(variant.color, Util.getColor(variant.color))

                var textView = TextView(this)
                textView.gravity = Gravity.CENTER
                textView.background = ContextCompat.getDrawable(this, colorMap.get(variant.color)!!)
                textView.layoutParams = layoutParams

                mActivityBinding.layoutColors.addView(textView)
            }

            if (!TextUtils.isEmpty(variant.size) && !sizeMap.containsKey(variant.size)) {
                sizeMap.put(variant.size, 1)

                var textView = TextView(this)

                textView.setText(variant.size)
                textView.gravity = Gravity.CENTER
                textView.background = ContextCompat.getDrawable(this, R.drawable.size_background)
                textView.layoutParams = layoutParams

                mActivityBinding.layoutSize.addView(textView)
            }
        }

        if (colorMap.isEmpty()) {
            mActivityBinding.dividerColor.visibility = View.GONE
            mActivityBinding.layoutColors.visibility = View.GONE
            mActivityBinding.headingAvailableColor.visibility = View.GONE
        }

        if (sizeMap.isEmpty()) {
            mActivityBinding.dividerSelectSize.visibility = View.GONE
            mActivityBinding.layoutSize.visibility = View.GONE
            mActivityBinding.headingSelectSize.visibility = View.GONE
        }
    }

    private fun setup() {
        mLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        mActivityBinding.recyclerProduct.setLayoutManager(mLayoutManager)
        mActivityBinding.recyclerProduct.setItemAnimator(DefaultItemAnimator())
        mActivityBinding.recyclerProduct.setAdapter(productAdapter)
    }

    override val viewModel: ProductDetailViewModel
        get() {
            productDetailViewModel = ViewModelProviders.of(this, mViewModelFactory).get(ProductDetailViewModel::class.java)
            return productDetailViewModel!!
        }

    override val bindingVariable: Int
        get() = BR.viewModel

    override val layoutId: Int
        get() = R.layout.activity_detail


}
