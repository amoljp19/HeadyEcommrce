package com.softaai.heady_e_commerce.model.dao

import android.arch.persistence.room.*
import com.softaai.heady_e_commerce.model.Category
import com.softaai.heady_e_commerce.model.CategoryProduct


/**
 * Created by Amol Pawar on 04-11-2018.
 * softAai Apps
 */

@Dao
interface CategoryProductDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCategoryProduct(categoryProduct: CategoryProduct)

    @Update
    fun updateCategoryProduct(categoryProduct: CategoryProduct)

    @Delete
    fun deleteCategoryProduct(categoryProduct: CategoryProduct)

    @Query("SELECT * FROM CategoryProduct")
    fun getCategoryProducts(): List<CategoryProduct>

}