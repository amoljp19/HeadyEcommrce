package com.softaai.heady_e_commerce.data.model.local

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey


/**
 * Created by Amol Pawar on 04-11-2018.
 * softAai Apps
 */

@Entity(tableName = "viewed_ranking")
class ViewedRanking {

    @PrimaryKey
    var id: Int = 0

    @ColumnInfo
    var view_count: Int = 0



}