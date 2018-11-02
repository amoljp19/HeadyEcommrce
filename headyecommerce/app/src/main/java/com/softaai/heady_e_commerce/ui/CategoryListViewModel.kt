package com.softaai.heady_e_commerce.ui

import android.view.View
import android.arch.lifecycle.MutableLiveData
import com.softaai.heady_e_commerce.base.BaseViewModel
import com.softaai.heady_e_commerce.network.RemoteServiceApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


/**
 * Created by Amol Pawar on 02-11-2018.
 * softAai Apps
 */

class CategoryListViewModel : BaseViewModel(){
    @Inject
    lateinit var remoteServiceApi: RemoteServiceApi

    private lateinit var subscription: Disposable

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()

    init{
        loadPosts()
    }

    private fun loadPosts(){
        subscription = remoteServiceApi.getCategories()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { onRetrieveCategoryListStart() }
                .doOnTerminate { onRetrieveCategoryListFinish() }
                .subscribe(
                        { onRetrieveCategoryListSuccess() },
                        { onRetrieveCategoryListError() }
                )
    }

    private fun onRetrieveCategoryListStart(){
        loadingVisibility.value = View.VISIBLE
    }

    private fun onRetrieveCategoryListFinish(){
        loadingVisibility.value = View.GONE
    }

    private fun onRetrieveCategoryListSuccess(){

    }

    private fun onRetrieveCategoryListError(){

    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }
}