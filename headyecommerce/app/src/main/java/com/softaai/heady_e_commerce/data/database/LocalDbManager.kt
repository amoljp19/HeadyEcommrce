package com.softaai.heady_e_commerce.data.database

import com.softaai.heady_e_commerce.data.model.local.*
import io.reactivex.Maybe


/**
 * Created by Amol Pawar on 04-11-2018.
 * softAai Apps
 */

interface LocalDbManager {

    fun insertCategoriesInDB(categoryList: List<Category>): Unit

    fun getAllCategoriesFromDB(): Maybe<List<Category>>

    fun getAllProductsFromDB(categoryId: Int): Maybe<List<Product>>

    fun getProductDetailFromDB(productId: Int): Maybe<Product>

    fun getSimilarProductsWithGivenCategoryIdFromDB(categoryId: Int, productId: Int): Maybe<List<Product>>

    fun insertAllProductsInDB(productList: List<Product>): Unit

    fun insertOrderedRankingInDB(orderedRankingList: List<OrderedRanking>): Unit

    fun insertSharedRankingInDB(sharedRankingList: List<SharedRanking>): Unit

    fun insertViewedRankingInDB(viewedRankingList: List<ViewedRanking>): Unit

    fun getOrderedRanking(): Maybe<List<OrderedRanking>>

    fun getSharedRanking(): Maybe<List<SharedRanking>>

    fun getViewedRanking(): Maybe<List<ViewedRanking>>

}