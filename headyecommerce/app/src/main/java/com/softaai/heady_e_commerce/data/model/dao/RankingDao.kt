package com.softaai.heady_e_commerce.data.model.dao

import android.arch.persistence.room.*
import com.softaai.heady_e_commerce.data.model.local.OrderedRanking
import com.softaai.heady_e_commerce.data.model.local.SharedRanking
import com.softaai.heady_e_commerce.data.model.local.ViewedRanking


/**
 * Created by Amol Pawar on 04-11-2018.
 * softAai Apps
 */

@Dao
interface RankingDao {
    @Query("SELECT * FROM ordered_ranking ORDER BY order_count DESC")
    fun getOrderedRanking(): List<OrderedRanking>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllOrderedRanking(orderedRanking: List<OrderedRanking>) : Unit

    @Query("SELECT * FROM shared_ranking ORDER BY shares DESC ")
    fun getSharedRanking(): List<SharedRanking>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllSharedRanking(sharedRanking: List<SharedRanking>) : Unit

    @Query("SELECT * FROM viewed_ranking ORDER BY view_count DESC")
    fun getViewedRanking(): List<ViewedRanking>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllViewedRanking(viewedRanking: List<ViewedRanking>) : Unit

}