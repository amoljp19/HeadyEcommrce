package com.softaai.heady_e_commerce.data.model.remote

import com.squareup.moshi.Json

/**
 * Created by Amol Pawar on 03-11-2018.
 * softAai Apps
 */
data class CategoryProductResponse(
        @field:Json(name = "id")val id:Int,
        @field:Json(name = "name")val name:String,
        @field:Json(name = "date_added")val dateAdded:String,
        @field:Json(name = "variants")val variantsList:List<VariantResponse>,
        @field:Json(name = "taxResponse")val taxResponse: TaxResponse
)
