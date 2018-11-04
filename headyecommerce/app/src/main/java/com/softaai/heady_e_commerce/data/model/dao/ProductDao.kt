package com.softaai.heady_e_commerce.data.model.dao

import android.arch.persistence.room.*
import com.softaai.heady_e_commerce.data.model.local.Product


/**
 * Created by Amol Pawar on 04-11-2018.
 * softAai Apps
 */

@Dao
interface ProductDao {

    @Query("SELECT * FROM product WHERE categoryId = :categoryId")
    fun getAllProducts(categoryId: Int): List<Product>

    @Query("SELECT * FROM product WHERE productId = :productId")
    fun getProductDetail(productId: Int): Product

    @Query("SELECT * FROM product WHERE categoryId = :categoryId AND NOT productId = :productId")
    fun getSimilarProductsWithGivenCategoryId(categoryId: Int, productId: Int): List<Product>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllProducts(productList: List<Product>): Unit
}