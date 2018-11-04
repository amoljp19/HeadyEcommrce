package com.softaai.heady_e_commerce.ui

import android.arch.lifecycle.MutableLiveData
import com.softaai.heady_e_commerce.base.BaseViewModel
import com.softaai.heady_e_commerce.model.remote.CategoryResponse
import com.softaai.heady_e_commerce.model.remote.CategoryProductResponse


/**
 * Created by Amol Pawar on 03-11-2018.
 * softAai Apps
 */
class CategoryViewModel:BaseViewModel() {

    private val categoryId = MutableLiveData<Int>()
    private val categoryName = MutableLiveData<String>()
    private val categoryProductsList = MutableLiveData<List<CategoryProductResponse>>()
    private val childCategoriesList = MutableLiveData<List<Int>>()

    fun bind(categoryResponse: CategoryResponse){
        categoryId.value = categoryResponse.id
        categoryName.value = categoryResponse.name
        categoryProductsList.value = categoryResponse.productsList
        childCategoriesList.value = categoryResponse.childCategoriesList

    }

    fun getCategoryId():MutableLiveData<Int>{
        return categoryId
    }

    fun getCategoryName():MutableLiveData<String>{
        return categoryName
    }

    fun getCategoryProductsList():MutableLiveData<List<CategoryProductResponse>>{
        return categoryProductsList
    }

    fun getChildCategoriesList():MutableLiveData<List<Int>>{
        return childCategoriesList
    }



}