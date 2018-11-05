package com.softaai.heady_e_commerce.data.model.remote

import com.squareup.moshi.Json

/**
 * Created by Amol Pawar on 03-11-2018.
 * softAai Apps
 */
data class RankingProductResponse(
        @field:Json(name = "id")val id:Int? = null,
        @field:Json(name = "view_count")val viewCount:Int? = null,
        @field:Json(name = "order_counts")val orderCount:Int? = null,
        @field:Json(name = "shares")val sharesCount:Int? = null
)
