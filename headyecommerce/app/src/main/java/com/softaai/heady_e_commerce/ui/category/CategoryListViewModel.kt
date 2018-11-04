package com.softaai.heady_e_commerce.ui

import android.view.View
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.softaai.heady_e_commerce.R
import com.softaai.heady_e_commerce.base.BaseViewModel
import com.softaai.heady_e_commerce.model.remote.MainResponse
import com.softaai.heady_e_commerce.model.dao.CategoryDao
import com.softaai.heady_e_commerce.network.RemoteServiceApi
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


/**
 * Created by Amol Pawar on 02-11-2018.
 * softAai Apps
 */

class CategoryListViewModel(categoryDao: CategoryDao) : BaseViewModel(){
    @Inject
    lateinit var remoteServiceApi: RemoteServiceApi

    private lateinit var subscription: Disposable

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()

    val errorMessage:MutableLiveData<Int> = MutableLiveData()
    val errorClickListener = View.OnClickListener {
        loadCategories(categoryDao) }

    val categoryListAdapter: CategoryListAdapter = CategoryListAdapter()



    init{
        loadCategories(categoryDao)
    }

    private fun loadCategories(categoryDao: CategoryDao) {
       subscription = Observable.fromCallable { categoryDao.getCategories() }
                .concatMap {
                    dbCategoryList ->
                    if(dbCategoryList.isEmpty())
                        remoteServiceApi.getMainResponse().concatMap {
                            apiCategoryList -> categoryDao.insertAll(*apiCategoryList.categoriesList.toTypedArray())
                            Observable.just(apiCategoryList)
                        }
                    else
                        Observable.just(dbCategoryList)
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { onRetrieveCategoryListStart() }
                .doOnTerminate { onRetrieveCategoryListFinish() }
                .subscribe(
                        {result -> onRetrieveCategoryListSuccess(result as MainResponse) },
                        {error -> onRetrieveCategoryListError(error) }
                )
    }

    private fun onRetrieveCategoryListStart(){
        loadingVisibility.value = View.VISIBLE
        errorMessage.value = null
    }

    private fun onRetrieveCategoryListFinish(){
        loadingVisibility.value = View.GONE
    }

    private fun onRetrieveCategoryListSuccess(result: MainResponse) {
        Log.d("Result", "There are ${result} categories in main response")
        categoryListAdapter.updateCategoryList(result.categoriesList)
        loadingVisibility.value = View.GONE
    }

    private fun onRetrieveCategoryListError(error: Throwable) {
        Log.d("Result", "There are ${error.message} categories in main response")
        error.printStackTrace()
        errorMessage.value = R.string.category_error
        loadingVisibility.value = View.GONE
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }
}