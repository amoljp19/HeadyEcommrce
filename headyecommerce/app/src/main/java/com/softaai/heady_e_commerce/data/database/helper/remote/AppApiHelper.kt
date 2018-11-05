package com.softaai.heady_e_commerce.data.database.helper.remote

import com.softaai.heady_e_commerce.data.model.remote.MainResponse
import io.reactivex.Maybe
import javax.inject.Inject
import javax.inject.Singleton


/**
 * Created by Amol Pawar on 05-11-2018.
 * softAai Apps
 */

@Singleton
class AppApiHelper: ApiHelper {

    @Inject
    constructor()

    override fun getDataFromServer(): Maybe<MainResponse> {
        return RemoteApiService.create().getProductData()
    }

}