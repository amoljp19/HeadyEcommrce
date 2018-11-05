package com.softaai.heady_e_commerce.data.model.remote

import com.squareup.moshi.Json

/**
 * Created by Amol Pawar on 03-11-2018.
 * softAai Apps
 */

data class MainResponse(
        @field:Json(name = "categories")val categoriesList: List<CategoryResponse>? = null,
        @field:Json(name = "rankings")val rankingsList: List<RankingResponse>? = null
)
