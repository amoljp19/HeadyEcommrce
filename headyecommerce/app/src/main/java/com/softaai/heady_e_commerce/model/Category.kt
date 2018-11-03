package com.softaai.heady_e_commerce.model

import com.squareup.moshi.Json

/**
 * Created by Amol Pawar on 03-11-2018.
 * softAai Apps
 */

data class Category(@field:Json(name = "id") val id: Int, @field:Json(name = "name") val name: String, @field:Json(name = "products")val productsList: List<CategoryProduct>, @field:Json(name = "child_categories")val childCategoriesList: List<Int>)
