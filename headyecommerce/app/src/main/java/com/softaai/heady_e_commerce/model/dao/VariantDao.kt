package com.softaai.heady_e_commerce.model.dao

import android.arch.persistence.room.*
import com.softaai.heady_e_commerce.model.Category
import com.softaai.heady_e_commerce.model.Variant


/**
 * Created by Amol Pawar on 04-11-2018.
 * softAai Apps
 */
@Dao
interface VariantDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertVariant(variant: Variant)

    @Update
    fun updateVariant(variant: Variant)

    @Delete
    fun deleteVariant(variant: Variant)

    @Query("SELECT * FROM Variant")
    fun getVariants(): List<Variant>

}