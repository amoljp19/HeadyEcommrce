package com.softaai.heady_e_commerce.ui.base

import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.View
import com.softaai.heady_e_commerce.ui.navigator.Navigator

/**
 * Created by Amol Pawar on 05-11-2018.
 * softAai Apps
 */
abstract class BaseViewHolder<T : ViewDataBinding, V : BaseViewModel<Navigator>?> : RecyclerView.ViewHolder   {

    val view: View

    lateinit var binding: T

    var viewModel: V ? = null

    constructor(itemView: View) : super(itemView) {
        this.view = itemView
    }

}