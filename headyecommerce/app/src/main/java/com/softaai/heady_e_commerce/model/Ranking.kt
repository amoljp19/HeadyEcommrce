package com.softaai.heady_e_commerce.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.TypeConverters
import com.softaai.heady_e_commerce.utils.converters.RankingProductsListConverter
import com.squareup.moshi.Json

/**
 * Created by Amol Pawar on 03-11-2018.
 * softAai Apps
 */
@Entity
data class Ranking(
        @PrimaryKey(autoGenerate = true)
        @field:Json(name = "ranking")val ranking:String,
        @TypeConverters(RankingProductsListConverter::class)
        @field:Json(name = "products")val rankingProductsList:List<RankingProduct>
)