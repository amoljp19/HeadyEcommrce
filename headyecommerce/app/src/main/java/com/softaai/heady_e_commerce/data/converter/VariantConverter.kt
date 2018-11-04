package com.softaai.heady_e_commerce.data.converter

import android.arch.persistence.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.softaai.heady_e_commerce.data.model.local.VariantInfo


/**
 * Created by Amol Pawar on 04-11-2018.
 * softAai Apps
 */

class VariantConverter {

    @TypeConverter
    fun fromString(value: String): ArrayList<VariantInfo> {
        val listType = object : TypeToken<ArrayList<VariantInfo>>() {

        }.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromVariantInfo(list: ArrayList<VariantInfo>): String {
        val gson = Gson()
        val json = gson.toJson(list)
        return json
    }

}