package com.softaai.heady_e_commerce.model.dao

import android.arch.persistence.room.*
import com.softaai.heady_e_commerce.model.Category


/**
 * Created by Amol Pawar on 04-11-2018.
 * softAai Apps
 */
@Dao
interface CategoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCategory(category: Category)

    @Update
    fun updateCategory(category: Category)

    @Delete
    fun deleteCategory(category: Category)

    @Query("SELECT * FROM Category")
    fun getCategories(): List<Category>

    @Insert
    fun insertAll(vararg category: Category)

}