package com.softaai.heady_e_commerce.ui.main.adapter

import android.databinding.ObservableArrayList
import android.databinding.ObservableField
import com.softaai.heady_e_commerce.data.database.DataManager
import com.softaai.heady_e_commerce.data.model.local.Category
import com.softaai.heady_e_commerce.data.model.local.Product
import com.softaai.heady_e_commerce.ui.base.BaseViewModel
import com.softaai.heady_e_commerce.ui.navigator.Navigator
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
/**
 * Created by Amol Pawar on 05-11-2018.
 * softAai Apps
 */
class CategoryViewModel(dataManager: DataManager) : BaseViewModel<Navigator>(dataManager) {

    var categoryName: ObservableField<String>
    var productList: ObservableArrayList<Product>
    lateinit var categoryViewListener : CategoryViewModelListener
    lateinit var mCategory: Category

    init {
        categoryName = ObservableField()
        productList = ObservableArrayList()
    }

    fun setCategory(category: Category) {
        mCategory = category
        categoryName = ObservableField<String>(category.categoryName)
        getProductsForThisCategory(category.categoryId)
    }

    fun setListener(categoryViewListener : CategoryViewModelListener) {
        this.categoryViewListener = categoryViewListener
    }

    fun getProductsForThisCategory(categoryId: Int) {
        setIsLoading(true)
        getCompositeDisposable().add(getDataManager()
                .getAllProductFromGivenCategory(categoryId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe( {
                    result -> result.size
                    productList.clear()
                    productList.addAll(result)

                    setIsLoading(false)
//                    Log.d("vikram", "category size - " + result.size)
                }))
    }

    interface CategoryViewModelListener {
        fun onMoreOptionClick(category: Category)
    }

    fun onMoreOptionClick() {
        categoryViewListener.onMoreOptionClick(mCategory)
    }

}