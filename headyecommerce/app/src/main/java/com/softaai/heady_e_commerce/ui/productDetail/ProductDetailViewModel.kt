package com.softaai.heady_e_commerce.ui.productDetail

import android.databinding.ObservableArrayList
import android.databinding.ObservableField
import android.graphics.drawable.Drawable
import android.support.v4.content.ContextCompat
import android.text.TextUtils
import com.softaai.heady_e_commerce.R
import com.softaai.heady_e_commerce.data.database.DataManager
import com.softaai.heady_e_commerce.data.model.local.Product
import com.softaai.heady_e_commerce.data.model.local.VariantInfo
import com.softaai.heady_e_commerce.ui.base.BaseViewModel
import com.softaai.heady_e_commerce.ui.navigator.Navigator
import com.softaai.heady_e_commerce.utils.App
import com.softaai.heady_e_commerce.utils.Util
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by Amol Pawar on 05-11-2018.
 * softAai Apps
 */
class ProductDetailViewModel(dataManager: DataManager) : BaseViewModel<Navigator>(dataManager) {

    var productName: ObservableField<String>
    var productImg: ObservableField<String>
    var productPrice: ObservableField<String>

    var isSizeAvailable: ObservableField<Boolean>
    var currentProductSize: ObservableField<String>

    var isColorAvailable: ObservableField<Boolean>
    var currentProductColor: ObservableField<Drawable>
    var taxInfo: ObservableField<String>
    var productList: ObservableArrayList<Product>

    init {
        productName = ObservableField()
        productImg = ObservableField()
        productPrice = ObservableField()
        productList = ObservableArrayList()
        taxInfo = ObservableField()
        currentProductColor = ObservableField()
        currentProductSize = ObservableField()
        isSizeAvailable = ObservableField()
        isColorAvailable = ObservableField()
    }

    fun setProduct(product: Product) {
        productName.set(product.productName)
        productImg.set(product.productName.substring(0,1))
        taxInfo.set(product.taxInfo!!.name + "  -  " + product.taxInfo!!.value + "%")

        setVariant(product.variantInfo!!.get(0))

        getSimilarProductsForThisCategory(product.categoryId, product.productId)
    }

    fun setVariant(variantInfo: VariantInfo) {
        productPrice.set(App.instance.getString(R.string.inr) + " " + variantInfo.price)
        currentProductSize.set(variantInfo.size)

        if (!TextUtils.isEmpty(variantInfo.size)) {
            isSizeAvailable.set(true)
        } else isSizeAvailable.set(false)

        currentProductColor.set(ContextCompat.getDrawable(App.instance, Util.getColor(variantInfo.color)))
        if (!TextUtils.isEmpty(variantInfo.color)) {
            isColorAvailable.set(true)
        } else isColorAvailable.set(false)


    }

    fun getSimilarProductsForThisCategory(categoryId: Int,  productId: Int) {
        setIsLoading(true)
        getCompositeDisposable().add(getDataManager()
                .getAllSimilarProducts(categoryId, productId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe( {
                    result -> result.size
                    productList.clear()
                    productList.addAll(result)

                    setIsLoading(false)
                }))
    }

}