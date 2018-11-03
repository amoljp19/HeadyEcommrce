package com.softaai.heady_e_commerce.model

import com.squareup.moshi.Json

/**
 * Created by Amol Pawar on 03-11-2018.
 * softAai Apps
 */

data class Ranking(@field:Json(name = "ranking")val ranking:String, @field:Json(name = "products")val rankingProductsList:List<RankingProduct>)