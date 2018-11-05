package com.softaai.heady_e_commerce.ui.main

import android.databinding.ObservableArrayList
import android.widget.Toast
import com.softaai.heady_e_commerce.R
import com.softaai.heady_e_commerce.ui.base.BaseViewModel
import com.softaai.heady_e_commerce.data.database.DataManager
import com.softaai.heady_e_commerce.data.model.local.Category
import com.softaai.heady_e_commerce.utils.App
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by Amol Pawar on 05-11-2018.
 * softAai Apps
 */
class MainViewModel(dataManager: DataManager) : BaseViewModel<MainNavigator>(dataManager) {

    val categoryList = ObservableArrayList<Category>()

    init {
        fetchDataFromServer()
    }

    fun fetchDataFromServer() {
        setIsLoading(true)
        getCompositeDisposable().add(getDataManager()
                .getAllCategory()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe( {
                    result -> result.size
                    categoryList.clear()
                    categoryList.addAll(result)
                    setIsLoading(false)
                }, {
                    it -> it.printStackTrace()
                    setIsLoading(false)
                    Toast.makeText(App.instance.applicationContext,
                            App.instance.applicationContext.getString(R.string.no_internet), Toast.LENGTH_SHORT).show()
                }))
    }
}

