package com.softaai.heady_e_commerce.di

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.persistence.room.Room
import android.support.v7.app.AppCompatActivity
import com.softaai.heady_e_commerce.model.database.AppDatabase
import com.softaai.heady_e_commerce.ui.CategoryListViewModel


/**
 * Created by Amol Pawar on 04-11-2018.
 * softAai Apps
 */

class ViewModelFactory(private val activity: AppCompatActivity): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CategoryListViewModel::class.java)) {
            val db = Room.databaseBuilder(activity.applicationContext, AppDatabase::class.java, "categories").build()
            @Suppress("UNCHECKED_CAST")
            return CategoryListViewModel(db.categoryDao()) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")

    }
}