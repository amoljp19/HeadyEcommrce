package com.softaai.heady_e_commerce.model

import com.squareup.moshi.Json

/**
 * Created by Amol Pawar on 03-11-2018.
 * softAai Apps
 */

data class Tax(@field:Json(name = "name")val name:String, @field:Json(name = "value")val value:Float)
