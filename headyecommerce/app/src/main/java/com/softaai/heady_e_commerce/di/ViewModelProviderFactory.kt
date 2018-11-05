package com.softaai.heady_e_commerce.di

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider


/**
 * Created by Amol Pawar on 05-11-2018.
 * softAai Apps
 */

class ViewModelProviderFactory<V : Any>(private val viewModel: V) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(viewModel.javaClass)) {
            return viewModel as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}
