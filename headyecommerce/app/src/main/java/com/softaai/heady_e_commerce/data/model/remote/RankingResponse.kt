package com.softaai.heady_e_commerce.data.model.remote

import com.squareup.moshi.Json

/**
 * Created by Amol Pawar on 03-11-2018.
 * softAai Apps
 */
data class RankingResponse(
        @field:Json(name = "ranking")val ranking:String? = null,
        @field:Json(name = "products")val rankingProductsListResponse:List<RankingProductResponse>? = null
)