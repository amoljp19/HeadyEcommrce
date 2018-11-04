package com.softaai.heady_e_commerce.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.squareup.moshi.Json

/**
 * Created by Amol Pawar on 03-11-2018.
 * softAai Apps
 */
@Entity
data class Variant(
        @PrimaryKey(autoGenerate = true)
        @field:Json(name = "id")val id:Int,
        @field:Json(name = "color")val color:String,
        @field:Json(name = "size")val size:Int?,
        @field:Json(name = "price")val price:Int
)
