package com.softaai.heady_e_commerce.data.database

import com.softaai.heady_e_commerce.data.database.helper.local.DBHelper
import com.softaai.heady_e_commerce.data.database.helper.remote.ApiHelper
import com.softaai.heady_e_commerce.data.model.local.*
import com.softaai.heady_e_commerce.data.model.remote.MainResponse
import com.softaai.heady_e_commerce.utils.RANKING_BY_ORDER
import com.softaai.heady_e_commerce.utils.RANKING_BY_SHARE
import com.softaai.heady_e_commerce.utils.RANKING_BY_VIEW
import io.reactivex.Maybe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


/**
 * Created by Amol Pawar on 04-11-2018.
 * softAai Apps
 */
class AppDataManager: DataManager {

    val dbHelper: DBHelper
    val apiHelper: ApiHelper

    @Inject
    constructor(dbHelper: DBHelper, apiHelper: ApiHelper) {
        this.dbHelper = dbHelper
        this.apiHelper = apiHelper
    }

    override fun getDataFromServer(): Maybe<MainResponse> {
        return apiHelper.getDataFromServer()
    }

    override fun insertCategoriesInDB(categoryList: List<Category>) {
        dbHelper.insertCategoriesInDB(categoryList)
    }

    override fun getAllCategoriesFromDB(): Maybe<List<Category>> {
        return dbHelper.getAllCategoriesFromDB().filter { result -> result.isNotEmpty() }
    }

    override fun getAllCategory(): Maybe<List<Category>> {
        var categoryFromDB = getAllCategoriesFromDB()
        var categoryFromServer = getCategoryFromServer()

        return Maybe.concat(categoryFromDB, categoryFromServer).firstElement()
    }

    fun getCategoryFromServer() : Maybe<List<Category>> {
        var response = getDataFromServer()
                .flatMap { result ->
                    //                    Log.d("vikram", "size - " + result.categories!!.size)
                    insertAllDataInDB(result)
                    getAllCategoriesFromDB()
                }

        response.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe( {
                    result ->
                    //                Log.d("vikram", "size - " + result.size)
                }, { })

        return response
    }

    fun insertAllDataInDB(apiResponse: MainResponse) {
        var categoryList = ArrayList<Category>()
        var productList =  ArrayList<Product>()
        var category: Category

        for (categoryResponse in apiResponse.categoriesList!!) {
            category = Category()
            category.categoryId = categoryResponse.id!!
            category.categoryName = categoryResponse.name!!

            var product: Product
            for (productResponse in categoryResponse.productsList!!) {
                product = Product()
                product.categoryId = categoryResponse.id!!
                product.productId = productResponse.id!!
                product.dataAdded = productResponse.dateAdded!!
                product.productName = productResponse.name!!

                var variant: VariantInfo
                var variantInfoList = ArrayList<VariantInfo>()
                for (variantResponse in productResponse.variantsList!!) {
                    variant = VariantInfo()
                    variant.id = variantResponse.id as Int
                    variant.color = variantResponse.color as String
                    variant.price = variantResponse.price as Int
                    variant.size = if (variantResponse.size == null) "" else (variantResponse.size as Integer).toString()

                    variantInfoList.add(variant)
                }

                product.variantInfo = variantInfoList

                var taxInfo = TaxInfo()
                taxInfo.name = productResponse.taxResponse?.name?:""
                taxInfo.value = productResponse.taxResponse?.value?:0f
                product.taxInfo  = taxInfo

                productList.add(product)
            }

            if (productList.size > 0) {
                categoryList.add(category)
                insertAllProductsInDB(productList)
            }
            productList.clear()
        }

        insertCategoriesInDB(categoryList)
        categoryList.clear()
        productList.clear()

        var orderedRankingList = ArrayList<OrderedRanking>()
        var sharedRankingList = ArrayList<SharedRanking>()
        var viewedRankingList = ArrayList<ViewedRanking>()
        for (rankingResponse in apiResponse.rankingsList!!) {

            if ("Most Viewed Products".equals(rankingResponse.ranking)) {
                for (viewCountResponse in rankingResponse.rankingProductsListResponse!!) {
                    var viewedRanking = ViewedRanking()
                    viewedRanking.id = viewCountResponse.id!!
                    viewedRanking.view_count = viewCountResponse.viewCount!!
                    viewedRankingList.add(viewedRanking)
                }
            } else if ("Most OrdeRed Products".equals(rankingResponse.ranking)) {
                for (viewCountResponse in rankingResponse.rankingProductsListResponse!!) {
                    var orderedRanking = OrderedRanking()
                    orderedRanking.id = viewCountResponse.id!!
                    orderedRanking.order_count = viewCountResponse.orderCount?:0
                    orderedRankingList.add(orderedRanking)
                }
            } else if ("Most ShaRed Products".equals(rankingResponse.ranking)) {
                for (viewCountResponse in rankingResponse.rankingProductsListResponse!!) {
                    var sharedRanking = SharedRanking()
                    sharedRanking.id = viewCountResponse.id!!
                    sharedRanking.shares = viewCountResponse.sharesCount!!
                    sharedRankingList.add(sharedRanking)
                }
            }
        }

        if (orderedRankingList.size > 0) {
            insertOrderedRankingInDB(orderedRankingList)
        }

        if (sharedRankingList.size > 0) {
            insertSharedRankingInDB(sharedRankingList)
        }

        if (viewedRankingList.size > 0) {
            insertViewedRankingInDB(viewedRankingList)
        }
    }

    override fun getAllProductsFromDB(categoryId: Int): Maybe<List<Product>> {
        return dbHelper.getAllProductsFromDB(categoryId)
    }

    override fun getAllProductFromGivenCategory(categoryId: Int): Maybe<List<Product>> {
        return dbHelper.getAllProductsFromDB(categoryId)
    }

    override fun getProductDetailFromDB(productId: Int): Maybe<Product> {
        return dbHelper.getProductDetailFromDB(productId)
    }

    override fun getSimilarProductsWithGivenCategoryIdFromDB(productId: Int, categoryId: Int): Maybe<List<Product>> {
        return dbHelper.getSimilarProductsWithGivenCategoryIdFromDB(productId, categoryId)
    }

    override fun insertAllProductsInDB(productList: List<Product>) {
        dbHelper.insertAllProductsInDB(productList)
    }

    override fun getAllSimilarProducts(categoryId: Int, productId: Int): Maybe<List<Product>> {
        return dbHelper.getSimilarProductsWithGivenCategoryIdFromDB(categoryId, productId)
    }

    override fun insertOrderedRankingInDB(orderedRankingList: List<OrderedRanking>) {
        return dbHelper.insertOrderedRankingInDB(orderedRankingList)
    }

    override fun insertSharedRankingInDB(sharedRankingList: List<SharedRanking>) {
        return dbHelper.insertSharedRankingInDB(sharedRankingList)
    }

    override fun insertViewedRankingInDB(viewedRankingList: List<ViewedRanking>) {
        return dbHelper.insertViewedRankingInDB(viewedRankingList)
    }

    override fun getOrderedRanking(): Maybe<List<OrderedRanking>> {
        return dbHelper.getOrderedRanking()
    }

    override fun getSharedRanking(): Maybe<List<SharedRanking>> {
        return dbHelper.getSharedRanking()
    }

    override fun getViewedRanking(): Maybe<List<ViewedRanking>> {
        return dbHelper.getViewedRanking()
    }

    override fun getAllProductByRanking(type: Int): Maybe<List<Product>> {
        when(type) {
            RANKING_BY_ORDER -> return getAllProductByOrderedRanking()
            RANKING_BY_SHARE -> return getAllProductBySharedRanking()
            RANKING_BY_VIEW -> return getAllProductByViewedRanking()
        }

        return Maybe.just(ArrayList<Product>())
    }

    fun getAllProductByOrderedRanking() : Maybe<List<Product>> {
        var response = getOrderedRanking().toObservable()
                .flatMapIterable { it }
                .flatMap { result -> getProductDetailFromDB(result.id).toObservable() }
                .toList()
                .toMaybe()


        response.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    //                    result -> Log.d("vikram", "products by ranking - " + result.size)
                })

        return response
    }

    fun getAllProductBySharedRanking(): Maybe<List<Product>> {
        var response = getSharedRanking().toObservable()
                .flatMapIterable { it }
                .flatMap { result -> getProductDetailFromDB(result.id).toObservable() }
                .toList()
                .toMaybe()


        response.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    //                    result -> Log.d("vikram", "products by ranking - " + result.size)
                })

        return response
    }

    fun getAllProductByViewedRanking(): Maybe<List<Product>> {
        var response = getViewedRanking().toObservable()
                .flatMapIterable { it }
                .flatMap { result -> getProductDetailFromDB(result.id).toObservable() }
                .toList()
                .toMaybe()


        response.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    //                    result -> Log.d("vikram", "products by ranking - " + result.size)
                })

        return response
    }

}