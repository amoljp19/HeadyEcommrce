package com.softaai.heady_e_commerce.data.model.dao

import android.arch.persistence.room.*
import com.softaai.heady_e_commerce.data.model.local.Category


/**
 * Created by Amol Pawar on 04-11-2018.
 * softAai Apps
 */
@Dao
interface CategoryDao {

    @Query("SELECT * FROM category")
    fun getAllCategories(): List<Category>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllCategories(categoryList: List<Category>) : Unit

}