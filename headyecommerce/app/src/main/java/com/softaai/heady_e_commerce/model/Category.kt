package com.softaai.heady_e_commerce.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.TypeConverters
import com.softaai.heady_e_commerce.utils.converters.CategoryProductsListConverter
import com.softaai.heady_e_commerce.utils.converters.IntListConverter
import com.squareup.moshi.Json

/**
 * Created by Amol Pawar on 03-11-2018.
 * softAai Apps
 */

@Entity
data class Category(
        @PrimaryKey (autoGenerate = true)
        @field:Json(name = "id") val id: Int,
        @field:Json(name = "name") val name: String,
        @TypeConverters(CategoryProductsListConverter::class)
        @field:Json(name = "products")val productsList: List<CategoryProduct>,
        @TypeConverters(IntListConverter::class)
        @field:Json(name = "child_categories")val childCategoriesList: List<Int>
)
