package com.softaai.heady_e_commerce.data.converter

import android.arch.persistence.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.softaai.heady_e_commerce.data.model.local.TaxInfo


/**
 * Created by Amol Pawar on 04-11-2018.
 * softAai Apps
 */

class TaxInfoConverter {

    @TypeConverter
    fun fromString(value: String): TaxInfo {
        val listType = object : TypeToken<TaxInfo>() {

        }.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromTaxInfo(taxInfo: TaxInfo): String {
        val gson = Gson()
        val json = gson.toJson(taxInfo)
        return json
    }

}