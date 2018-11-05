package com.softaai.heady_e_commerce.data.database

import com.softaai.heady_e_commerce.data.database.helper.local.DBHelper
import com.softaai.heady_e_commerce.data.database.helper.remote.ApiHelper
import com.softaai.heady_e_commerce.data.model.local.*
import io.reactivex.Maybe


/**
 * Created by Amol Pawar on 04-11-2018.
 * softAai Apps
 */

interface DataManager: ApiHelper, DBHelper {

    fun getAllCategory() : Maybe<List<Category>>

    fun getAllProductFromGivenCategory(categoryId: Int) : Maybe<List<Product>>

    fun getAllSimilarProducts(categoryId: Int, productId: Int) : Maybe<List<Product>>

    fun getAllProductByRanking(type: Int) : Maybe<List<Product>>

}