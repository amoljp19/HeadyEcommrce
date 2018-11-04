package com.softaai.heady_e_commerce.data.model.remote

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.TypeConverters
import com.softaai.heady_e_commerce.utils.converters.RankingProductsListConverter
import com.squareup.moshi.Json

/**
 * Created by Amol Pawar on 03-11-2018.
 * softAai Apps
 */
data class RankingResponse(
        val id:Int,
        @field:Json(name = "ranking")val ranking:String,
        @field:Json(name = "products")val rankingProductsListResponse:List<RankingProductResponse>
)