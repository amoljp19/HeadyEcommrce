package com.softaai.heady_e_commerce.data.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import android.content.Context
import com.softaai.heady_e_commerce.data.converter.TaxInfoConverter
import com.softaai.heady_e_commerce.data.converter.VariantConverter
import com.softaai.heady_e_commerce.data.model.dao.CategoryDao
import com.softaai.heady_e_commerce.data.model.dao.ProductDao
import com.softaai.heady_e_commerce.data.model.dao.RankingDao
import com.softaai.heady_e_commerce.data.model.local.*


/**
 * Created by Amol Pawar on 04-11-2018.
 * softAai Apps
 */

@Database(entities = [Category::class, Product::class, OrderedRanking::class,
    SharedRanking::class, ViewedRanking::class], version = 1)
@TypeConverters(TaxInfoConverter::class, VariantConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun categoryDao(): CategoryDao

    abstract fun productDao(): ProductDao

    abstract fun rankingDao(): RankingDao

    companion object {
        var INSTANCE: AppDatabase? = null

        fun getAppDataBase(context: Context): AppDatabase? {
            if (INSTANCE == null){
                synchronized(AppDatabase::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "HeadyDB").build()
                }
            }
            return INSTANCE
        }

        fun destroyDataBase(){
            INSTANCE = null
        }
    }
}

