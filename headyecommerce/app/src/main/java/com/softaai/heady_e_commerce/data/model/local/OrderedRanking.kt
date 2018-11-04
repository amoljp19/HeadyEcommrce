package com.softaai.heady_e_commerce.data.model.local

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey


/**
 * Created by Amol Pawar on 04-11-2018.
 * softAai Apps
 */

@Entity(tableName = "ordered_ranking")
class OrderedRanking {

    @PrimaryKey
    var id: Int = 0

    @ColumnInfo
    var order_count: Int = 0

}