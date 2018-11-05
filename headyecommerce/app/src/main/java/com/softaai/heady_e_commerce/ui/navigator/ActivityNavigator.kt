package com.softaai.heady_e_commerce.ui.navigator

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
/**
 * Created by Amol Pawar on 05-11-2018.
 * softAai Apps
 */
class ActivityNavigator(appActivity: AppCompatActivity) : Navigator {

    var activity = appActivity

    override fun startActivity(intent: Intent) {
        activity.startActivity(intent)
    }
}