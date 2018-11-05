package com.softaai.heady_e_commerce.di.module

import android.support.v7.app.AppCompatActivity
import com.softaai.heady_e_commerce.di.annotations.PerActivity
import com.softaai.heady_e_commerce.ui.navigator.ActivityNavigator
import com.softaai.heady_e_commerce.ui.navigator.Navigator
import dagger.Module
import dagger.Provides
import javax.inject.Inject


/**
 * Created by Amol Pawar on 05-11-2018.
 * softAai Apps
 */

@Module
class ActivityModule {

    var mActivity : AppCompatActivity

    @Inject
    constructor(activity: AppCompatActivity) {
        this.mActivity = activity
    }


    @Provides
    @PerActivity
    fun provideNavigator(): Navigator {
        return ActivityNavigator(mActivity)
    }


}