package com.softaai.heady_e_commerce.ui.base

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import com.softaai.heady_e_commerce.data.database.DataManager
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by Amol Pawar on 05-11-2018.
 * softAai Apps
 */
abstract class BaseViewModel<N> : ViewModel {

    private var mNavigator: N ? = null
    private val mDataManager: DataManager
    private var isLoading = ObservableField(false)

    private val mCompositeDisposable: CompositeDisposable

    constructor(dataManager: DataManager) {
        this.mDataManager = dataManager
        this.mCompositeDisposable = CompositeDisposable()
    }

    fun setNavigator(navigator: N) {
        this.mNavigator = navigator
    }

    fun getNavigator(): N {
        return mNavigator!!
    }

    fun getDataManager(): DataManager {
        return mDataManager
    }

    fun getCompositeDisposable(): CompositeDisposable {
        return mCompositeDisposable
    }

    override fun onCleared() {
        mCompositeDisposable.dispose()
        super.onCleared()
    }

    fun getIsLoading() : ObservableField<Boolean> {
        return isLoading
    }

    fun setIsLoading(boolean: Boolean) {
        this.isLoading.set(boolean)
    }

}