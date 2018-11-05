package com.softaai.heady_e_commerce.data.model.remote

import com.squareup.moshi.Json

/**
 * Created by Amol Pawar on 03-11-2018.
 * softAai Apps
 */
data class TaxResponse(
        @field:Json(name = "name")val name:String,
        @field:Json(name = "value")val value:Float
)
