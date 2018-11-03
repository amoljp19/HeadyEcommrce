package com.softaai.heady_e_commerce.ui

import android.arch.lifecycle.MutableLiveData
import com.softaai.heady_e_commerce.base.BaseViewModel
import com.softaai.heady_e_commerce.model.Category
import com.softaai.heady_e_commerce.model.CategoryProduct


/**
 * Created by Amol Pawar on 03-11-2018.
 * softAai Apps
 */
class CategoryViewModel:BaseViewModel() {

    private val categoryId = MutableLiveData<Int>()
    private val categoryName = MutableLiveData<String>()
    private val categoryProductsList = MutableLiveData<List<CategoryProduct>>()
    private val childCategoriesList = MutableLiveData<List<Int>>()

    fun bind(category: Category){
        categoryId.value = category.id
        categoryName.value = category.name
        categoryProductsList.value = category.productsList
        childCategoriesList.value = category.childCategoriesList

    }

    fun getCategoryId():MutableLiveData<Int>{
        return categoryId
    }

    fun getCategoryName():MutableLiveData<String>{
        return categoryName
    }

    fun getCategoryProductsList():MutableLiveData<List<CategoryProduct>>{
        return categoryProductsList
    }

    fun getChildCategoriesList():MutableLiveData<List<Int>>{
        return childCategoriesList
    }



}