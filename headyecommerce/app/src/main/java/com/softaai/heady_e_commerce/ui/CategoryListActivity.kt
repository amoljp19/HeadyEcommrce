package com.softaai.heady_e_commerce.ui

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.softaai.heady_e_commerce.R

import com.softaai.heady_e_commerce.databinding.ActivityCategoryListBinding


/**
 * Created by Amol Pawar on 02-11-2018.
 * softAai Apps
 */

class CategoryListActivity: AppCompatActivity() {
    private lateinit var binding: ActivityCategoryListBinding
    private lateinit var viewModel: CategoryListViewModel

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_category_list)
        binding.categoryList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        viewModel = ViewModelProviders.of(this).get(CategoryListViewModel::class.java)
        binding.viewModel = viewModel
    }
}