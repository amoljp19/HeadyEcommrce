package com.softaai.heady_e_commerce.ui.ranking

import android.databinding.ObservableArrayList
import android.util.Log
import android.widget.Toast
import com.softaai.heady_e_commerce.R
import com.softaai.heady_e_commerce.data.database.DataManager
import com.softaai.heady_e_commerce.data.model.local.Product
import com.softaai.heady_e_commerce.ui.base.BaseViewModel
import com.softaai.heady_e_commerce.ui.navigator.Navigator
import com.softaai.heady_e_commerce.utils.App
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
/**
 * Created by Amol Pawar on 05-11-2018.
 * softAai Apps
 */
class ProductRankingViewModel(dataManager: DataManager) : BaseViewModel<Navigator>(dataManager) {

    var productList: ObservableArrayList<Product>

    init {
        productList = ObservableArrayList()
    }

    fun getProductByRanking(orderType: Int) {
        setIsLoading(true)
        getCompositeDisposable().add(getDataManager()
                .getAllProductByRanking(orderType)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe( {
                    result -> result.size
                    productList.clear()
                    productList.addAll(result)
                    setIsLoading(false)
                }, {
                    setIsLoading(false)
                    Toast.makeText(App.instance.applicationContext,
                            App.instance.applicationContext.getString(R.string.no_internet), Toast.LENGTH_SHORT).show()
                }))
    }

}