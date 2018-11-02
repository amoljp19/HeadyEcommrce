package com.softaai.heady_e_commerce.network

import retrofit2.http.GET
import io.reactivex.Observable


/**
 * Created by Amol Pawar on 02-11-2018.
 * softAai Apps
 */

interface RemoteServiceApi{

    @GET("/json")
    fun getCategories(): Observable<List<Category>>

}