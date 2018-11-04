package com.softaai.heady_e_commerce.model.dao

import android.arch.persistence.room.*
import com.softaai.heady_e_commerce.model.Ranking
import com.softaai.heady_e_commerce.model.RankingProduct


/**
 * Created by Amol Pawar on 04-11-2018.
 * softAai Apps
 */
@Dao
interface RankingProductDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRankingProduct(rankingProduct: RankingProduct)

    @Update
    fun updateRankingProduct(rankingProduct: RankingProduct)

    @Delete
    fun deleteRankingProduct(rankingProduct: RankingProduct)

    @Query("SELECT * FROM RankingProduct")
    fun getRankingProducts(): List<RankingProduct>

}