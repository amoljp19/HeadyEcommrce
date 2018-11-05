package com.softaai.heady_e_commerce.di.module

import android.app.Application
import android.arch.persistence.room.Room
import android.content.Context
import com.softaai.heady_e_commerce.data.database.AppDataManager
import com.softaai.heady_e_commerce.data.database.AppDatabase
import com.softaai.heady_e_commerce.data.database.DataManager
import com.softaai.heady_e_commerce.data.database.helper.local.AppDBHelper
import com.softaai.heady_e_commerce.data.database.helper.local.DBHelper
import com.softaai.heady_e_commerce.data.database.helper.remote.ApiHelper
import com.softaai.heady_e_commerce.data.database.helper.remote.AppApiHelper
import com.softaai.heady_e_commerce.di.annotations.DatabaseInfo
import com.softaai.heady_e_commerce.utils.DB_NAME
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


/**
 * Created by Amol Pawar on 05-11-2018.
 * softAai Apps
 */

@Module
class AppModule {
    @Provides
    @Singleton
    fun provideContext(application: Application): Context {
        return application
    }

    @Provides
    @DatabaseInfo
    fun provideDatabaseName(): String {
        return DB_NAME
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@DatabaseInfo dbName: String, context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, dbName).fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build()
    }

    @Provides
    @Singleton
    fun provideDbHelper(appDbHelper: AppDBHelper): DBHelper {
        return appDbHelper
    }

    @Provides
    @Singleton
    fun provideApiHelper(appApiHelper: AppApiHelper): ApiHelper {
        return appApiHelper
    }

    @Provides
    @Singleton
    fun provideDataManager(appDataManager: AppDataManager): DataManager {
        return appDataManager
    }

}