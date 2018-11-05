package com.softaai.heady_e_commerce.data.model.remote

import com.squareup.moshi.Json

/**
 * Created by Amol Pawar on 03-11-2018.
 * softAai Apps
 */
data class RankingProductResponse(
        @field:Json(name = "id")val id:Int,
        @field:Json(name = "view_count")val viewCount:Int,
        @field:Json(name = "order_counts")val orderCount:Int,
        @field:Json(name = "shares")val sharesCount:Int
)
