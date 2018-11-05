package com.softaai.heady_e_commerce.data.model.remote

import com.squareup.moshi.Json

/**
 * Created by Amol Pawar on 03-11-2018.
 * softAai Apps
 */
data class VariantResponse(
        @field:Json(name = "id")val id:Int? = null,
        @field:Json(name = "color")val color:String? = null,
        @field:Json(name = "size")val size:Int? = null,
        @field:Json(name = "price")val price:Int? = null
)
