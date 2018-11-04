package com.softaai.heady_e_commerce.model

import android.arch.persistence.room.TypeConverters
import com.softaai.heady_e_commerce.utils.converters.CategoryListConverter
import com.softaai.heady_e_commerce.utils.converters.RankingListConverter
import com.squareup.moshi.Json

/**
 * Created by Amol Pawar on 03-11-2018.
 * softAai Apps
 */

data class MainResponse(
        @TypeConverters(CategoryListConverter::class)
        @field:Json(name = "categories")val categoriesList: List<Category>,
        @TypeConverters(RankingListConverter::class)
        @field:Json(name = "rankings")val rankingsList: List<Ranking>
)
