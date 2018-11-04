package com.softaai.heady_e_commerce.model.dao

import android.arch.persistence.room.*
import com.softaai.heady_e_commerce.model.Ranking
import com.softaai.heady_e_commerce.model.Tax


/**
 * Created by Amol Pawar on 04-11-2018.
 * softAai Apps
 */
@Dao
interface TaxDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTax(tax: Tax)

    @Update
    fun updateTax(tax: Tax)

    @Delete
    fun deleteTax(tax: Tax)
    @Query("SELECT * FROM Tax")
    fun getTaxes(): List<Tax>

}