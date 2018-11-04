package com.softaai.heady_e_commerce.model.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import android.content.Context
import com.softaai.heady_e_commerce.model.*
import com.softaai.heady_e_commerce.model.dao.*
import com.softaai.heady_e_commerce.utils.converters.*


/**
 * Created by Amol Pawar on 04-11-2018.
 * softAai Apps
 */

@Database(entities = [Category::class, CategoryProduct::class, Ranking::class, RankingProduct::class, Tax::class, Variant::class], version = 1)
@TypeConverters(CategoryListConverter::class, CategoryProductsListConverter::class, IntListConverter::class, RankingListConverter::class, RankingProductsListConverter::class, VariantListConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun categoryDao(): CategoryDao
    abstract fun categoryProductDao(): CategoryProductDao
    abstract fun rankingDao(): RankingDao
    abstract fun rankingProductDao(): RankingProductDao
    abstract fun taxDao(): TaxDao
    abstract fun variantDao(): VariantDao

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