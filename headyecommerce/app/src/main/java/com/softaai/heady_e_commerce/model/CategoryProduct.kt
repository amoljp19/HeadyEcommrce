package com.softaai.heady_e_commerce.model

/**
 * Created by Amol Pawar on 03-11-2018.
 * softAai Apps
 */

data class CategoryProduct(val id:Int, val name:String, val dateAdded:String, val variantsList:List<Variant>, val tax:Tax)
