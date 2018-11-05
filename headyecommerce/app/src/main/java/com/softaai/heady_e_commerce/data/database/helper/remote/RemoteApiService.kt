package com.softaai.heady_e_commerce.data.database.helper.remote

import com.softaai.heady_e_commerce.data.model.remote.MainResponse
import com.softaai.heady_e_commerce.utils.BASE_URL
import io.reactivex.Maybe
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET


/**
 * Created by Amol Pawar on 05-11-2018.
 * softAai Apps
 */

interface RemoteApiService {

    @GET("/json")
    fun getProductData(): Maybe<MainResponse>

    companion object Factory {
        fun create(): RemoteApiService {
            val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(MoshiConverterFactory.create())
                    .build()

            return retrofit.create(RemoteApiService::class.java)
        }
    }

}