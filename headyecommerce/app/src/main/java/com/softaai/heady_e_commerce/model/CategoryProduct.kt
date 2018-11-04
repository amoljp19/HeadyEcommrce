package com.softaai.heady_e_commerce.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.TypeConverters
import com.softaai.heady_e_commerce.utils.converters.VariantListConverter
import com.squareup.moshi.Json

/**
 * Created by Amol Pawar on 03-11-2018.
 * softAai Apps
 */
@Entity
data class CategoryProduct(
        @PrimaryKey
        @field:Json(name = "id")val id:Int,
        @field:Json(name = "name")val name:String,
        @field:Json(name = "date_added")val dateAdded:String,
        @TypeConverters(VariantListConverter::class)
        @field:Json(name = "variants")val variantsList:List<Variant>,
        @field:Json(name = "tax")val tax:Tax
)
