package com.softaai.heady_e_commerce.ui.main.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.softaai.heady_e_commerce.data.model.local.Category
import com.softaai.heady_e_commerce.databinding.ItemCategoryBinding

/**
 * Created by Amol Pawar on 05-11-2018.
 * softAai Apps
 */
class CategoryAdapter(categoryList: MutableList<Category>) : RecyclerView.Adapter<CategoryViewHolder>() {

    var mItems = categoryList

    override fun getItemCount(): Int {
        return mItems.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        var itemCategory = ItemCategoryBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false)
        var categoryViewHolder = CategoryViewHolder(itemCategory)
        return categoryViewHolder
    }

    override fun onBindViewHolder(categoryViewHolder: CategoryViewHolder, position: Int) {
        categoryViewHolder.bind(mItems.get(position))
    }

    fun clearItems() {
        mItems.clear()
    }

    fun addItems(categoryList: List<Category>) {
        mItems.addAll(categoryList)
        notifyDataSetChanged()
    }


}