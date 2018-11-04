package com.softaai.heady_e_commerce.model.dao

import android.arch.persistence.room.*
import com.softaai.heady_e_commerce.model.Category
import com.softaai.heady_e_commerce.model.Ranking


/**
 * Created by Amol Pawar on 04-11-2018.
 * softAai Apps
 */

@Dao
interface RankingDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRanking(ranking: Ranking)

    @Update
    fun updateRanking(ranking: Ranking)

    @Delete
    fun deleteRanking(ranking: Ranking)

    @Query("SELECT * FROM Ranking")
    fun getRankings(): List<Ranking>

}