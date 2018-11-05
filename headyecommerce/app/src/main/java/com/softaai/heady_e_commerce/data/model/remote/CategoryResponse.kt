package com.softaai.heady_e_commerce.data.model.remote

import com.squareup.moshi.Json

/**
 * Created by Amol Pawar on 03-11-2018.
 * softAai Apps
 */

data class CategoryResponse(
        @field:Json(name = "id") val id: Int,
        @field:Json(name = "name") val name: String,
        @field:Json(name = "products")val productsList: List<CategoryProductResponse>,
        @field:Json(name = "child_categories")val childCategoriesList: List<Int>
)
