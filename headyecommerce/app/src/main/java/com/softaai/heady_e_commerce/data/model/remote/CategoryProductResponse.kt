package com.softaai.heady_e_commerce.data.model.remote

import android.arch.persistence.room.TypeConverters
import com.softaai.heady_e_commerce.data.converter.TaxInfoConverter
import com.softaai.heady_e_commerce.data.converter.VariantConverter
import com.squareup.moshi.Json

/**
 * Created by Amol Pawar on 03-11-2018.
 * softAai Apps
 */
data class CategoryProductResponse(
        @field:Json(name = "id")val id:Int? = null,
        @field:Json(name = "name")val name:String? = null,
        @field:Json(name = "date_added")val dateAdded:String? = null,
        @TypeConverters(VariantConverter::class)
        @field:Json(name = "variants")val variantsList:List<VariantResponse>? = null,
        @TypeConverters(TaxInfoConverter::class)
        @field:Json(name = "taxResponse")val taxResponse: TaxResponse? = null
)
