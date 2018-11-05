package com.softaai.heady_e_commerce.ui.main

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import com.softaai.heady_e_commerce.BR
import com.softaai.heady_e_commerce.R
import javax.inject.Inject
import com.softaai.heady_e_commerce.databinding.ActivityMainBinding
import com.softaai.heady_e_commerce.di.component.DaggerMainActivityComponent
import com.softaai.heady_e_commerce.ui.base.BaseActivity
import com.softaai.heady_e_commerce.ui.main.adapter.CategoryAdapter
import com.softaai.heady_e_commerce.ui.productDetail.ProductDetailActivity
import com.softaai.heady_e_commerce.ui.ranking.ProductRankingActivity
import com.softaai.heady_e_commerce.utils.App
import com.softaai.heady_e_commerce.utils.RANKING_BY_ORDER
import com.softaai.heady_e_commerce.utils.RANKING_BY_SHARE
import com.softaai.heady_e_commerce.utils.RANKING_BY_VIEW

/**
 * Created by Amol Pawar on 05-11-2018.
 * softAai Apps
 */

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(), MainNavigator {

    @Inject
    lateinit var mViewModelFactory: ViewModelProvider.Factory

    lateinit var mActivityMainBinding: ActivityMainBinding

    @Inject
    lateinit var mLayoutManager: LinearLayoutManager

    @Inject
    lateinit var categoryAdapter: CategoryAdapter

    var mainViewModel: MainViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        DaggerMainActivityComponent
                .builder()
                .appComponent(App.instance.appComponent)
                .build()
                .injectMainActivity(this)


        super.onCreate(savedInstanceState)


        mActivityMainBinding = viewDataBinding!!
        viewModel.setNavigator(this)
        setup()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.getItemId()) {
            R.id.most_viewed -> {
                var intent = Intent(this, ProductRankingActivity::class.java)
                intent.putExtra("type", RANKING_BY_VIEW)
                startActivity(intent)
            }
            R.id.most_ordered -> {
                var intent = Intent(this, ProductRankingActivity::class.java)
                intent.putExtra("type", RANKING_BY_ORDER)
                startActivity(intent)
            }
            R.id.most_shared -> {
                var intent = Intent(this, ProductRankingActivity::class.java)
                intent.putExtra("type", RANKING_BY_SHARE)
                startActivity(intent)
            }
        }
        return true
    }

    private fun setup() {
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL)
        mActivityMainBinding.blogRecyclerView.setLayoutManager(mLayoutManager)
        mActivityMainBinding.blogRecyclerView.setItemAnimator(DefaultItemAnimator())
        mActivityMainBinding.blogRecyclerView.setAdapter(categoryAdapter)
    }

    override fun openProductDetailActivity() {
        var openDetailIntent = Intent(this, ProductDetailActivity::class.java)
        startActivity(openDetailIntent)
    }

    override fun handleError(throwable: Throwable) {
        
    }

    override val bindingVariable: Int
        get() = BR.viewModel
    
    
    override val layoutId: Int
        get() = R.layout.activity_main


    override val viewModel : MainViewModel
        get() {
            mainViewModel = ViewModelProviders.of(this, mViewModelFactory).get(MainViewModel::class.java)
            return mainViewModel!!
        }
}
