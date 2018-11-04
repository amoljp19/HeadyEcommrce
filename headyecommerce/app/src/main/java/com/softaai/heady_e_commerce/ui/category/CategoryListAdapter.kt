package com.softaai.heady_e_commerce.ui

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.softaai.heady_e_commerce.R
import com.softaai.heady_e_commerce.data.model.remote.CategoryResponse

import com.softaai.heady_e_commerce.databinding.ItemCategoryBinding




/**
 * Created by Amol Pawar on 03-11-2018.
 * softAai Apps
 */

class CategoryListAdapter: RecyclerView.Adapter<CategoryListAdapter.ViewHolder>() {
    private lateinit var categoryResponseList:List<CategoryResponse>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryListAdapter.ViewHolder {
        val binding: ItemCategoryBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_category, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryListAdapter.ViewHolder, position: Int) {
        holder.bind(categoryResponseList[position])
    }

    override fun getItemCount(): Int {
        return if(::categoryResponseList.isInitialized) categoryResponseList.size else 0
    }

    fun updateCategoryList(categoryResponseList:List<CategoryResponse>){
        this.categoryResponseList = categoryResponseList
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ItemCategoryBinding):RecyclerView.ViewHolder(binding.root){
        private val viewModel = CategoryViewModel()

        fun bind(categoryResponse: CategoryResponse){
            viewModel.bind(categoryResponse)
            binding.viewModel = viewModel
        }
    }
}