package com.softaai.heady_e_commerce.utils.converters

import android.arch.persistence.room.TypeConverter
import android.widget.TextView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.softaai.heady_e_commerce.model.Tax
import com.softaai.heady_e_commerce.model.Variant
import org.w3c.dom.Text
import java.util.*


/**
 * Created by Amol Pawar on 04-11-2018.
 * softAai Apps
 */
class TaxObjectConverter {

    var gson = Gson()

    @TypeConverter
    fun fromStringToTaxObject(data: String?): Tax? {
        if(data == null)
        {
            return null
        }
        val objectType = object : TypeToken<Tax>() {}.type
        return gson.fromJson(data, objectType)
    }

    @TypeConverter
    fun taxObjectToString(someObjects: Tax?): String? {
        return gson.toJson(someObjects)
    }
}