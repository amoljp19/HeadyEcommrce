package com.softaai.heady_e_commerce.di.module

import android.app.Application
import android.arch.persistence.room.Room
import android.content.Context
import com.softaai.heady_e_commerce.data.database.AppDatabase
import com.softaai.heady_e_commerce.data.database.AppDatabaseManager
import com.softaai.heady_e_commerce.data.database.LocalDbManager
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

//    @Provides
//    @DatabaseInfo
//    fun provideDatabaseName(): String {
//        return AppConstants.DB_NAME
//    }
//
//    @Provides
//    @Singleton
//    fun provideAppDatabase(@DatabaseInfo dbName: String, context: Context): AppDatabase {
//        return Room.databaseBuilder(context, AppDatabase::class.java, dbName).fallbackToDestructiveMigration()
//                .allowMainThreadQueries()
//                .build()
//    }
//
//    @Provides
//    @Singleton
//    fun provideDbHelper(appDbHelper: AppDBHelper): DBHelper {
//        return appDbHelper
//    }
//
//    @Provides
//    @Singleton
//    fun provideApiHelper(appApiHelper: AppApiHelper): ApiHelper {
//        return appApiHelper
//    }

    @Provides
    @Singleton
    fun provideLocalDbManager(appDataManager: AppDatabaseManager): LocalDbManager {
        return appDataManager
    }

}