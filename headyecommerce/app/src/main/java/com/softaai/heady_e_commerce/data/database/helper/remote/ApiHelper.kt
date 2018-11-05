package com.softaai.heady_e_commerce.data.database.helper.remote

import com.softaai.heady_e_commerce.data.model.remote.MainResponse
import io.reactivex.Maybe


/**
 * Created by Amol Pawar on 05-11-2018.
 * softAai Apps
 */

interface ApiHelper {

    fun getDataFromServer() : Maybe<MainResponse>

}