package com.softaai.heady_e_commerce.model

/**
 * Created by Amol Pawar on 03-11-2018.
 * softAai Apps
 */

data class Category(val id: Int, val name: String, val productsList: List<CategoryProduct>,val childCategoriesList: List<Int>)
