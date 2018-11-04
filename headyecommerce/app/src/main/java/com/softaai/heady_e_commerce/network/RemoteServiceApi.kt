package com.softaai.heady_e_commerce.network

import com.softaai.heady_e_commerce.data.model.remote.MainResponse
import retrofit2.http.GET
import io.reactivex.Observable


/**
 * Created by Amol Pawar on 02-11-2018.
 * softAai Apps
 */

interface RemoteServiceApi{

    @GET("json")
    fun getMainResponse():Observable<MainResponse>

//    @GET("/json")
//    fun getCategoriesResponse():Observable<List<CategoryResponse>>

}