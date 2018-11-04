package com.softaai.heady_e_commerce.data.database

import com.softaai.heady_e_commerce.data.model.local.*
import com.softaai.heady_e_commerce.data.model.remote.CategoryResponse
import com.softaai.heady_e_commerce.data.model.remote.MainResponse
import io.reactivex.Maybe
import javax.inject.Inject


/**
 * Created by Amol Pawar on 04-11-2018.
 * softAai Apps
 */
class AppDatabaseManager : LocalDbManager {

    val appDatabase: AppDatabase

    @Inject
    constructor(database: AppDatabase) {
        this.appDatabase = database
    }


    override fun getAllProductsFromDB(categoryId: Int): Maybe<List<Product>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
       appDatabase.productDao().getAllProducts(categoryId);
    }

    override fun getProductDetailFromDB(productId: Int): Maybe<Product> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        appDatabase.productDao().getProductDetail(productId)
    }

    override fun getSimilarProductsWithGivenCategoryIdFromDB(categoryId: Int, productId: Int): Maybe<List<Product>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        appDatabase.productDao().getSimilarProductsWithGivenCategoryId(categoryId, productId)
    }

    override fun insertAllProductsInDB(productList: List<Product>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        appDatabase.productDao().insertAllProducts(productList)
    }

    override fun insertOrderedRankingInDB(orderedRankingList: List<OrderedRanking>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        appDatabase.rankingDao().insertAllOrderedRanking(orderedRankingList)
    }

    override fun insertSharedRankingInDB(sharedRankingList: List<SharedRanking>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        appDatabase.rankingDao().insertAllSharedRanking(sharedRankingList)
    }

    override fun insertViewedRankingInDB(viewedRankingList: List<ViewedRanking>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        appDatabase.rankingDao().insertAllViewedRanking(viewedRankingList)
    }

    override fun getOrderedRanking(): Maybe<List<OrderedRanking>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        appDatabase.rankingDao().getOrderedRanking()
    }

    override fun getSharedRanking(): Maybe<List<SharedRanking>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        appDatabase.rankingDao().getSharedRanking()
    }

    override fun getViewedRanking(): Maybe<List<ViewedRanking>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        appDatabase.rankingDao().getViewedRanking()
    }

    override fun getAllCategoriesFromDB(): Maybe<List<Category>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        appDatabase.categoryDao().getAllCategories()
    }

    override fun insertCategoriesInDB(categoryList: List<Category>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        appDatabase.categoryDao().insertAllCategories(categoryList)
    }

    fun insertAllDataInDB(mainResponse: MainResponse) {
        var categoryList = ArrayList<Category>()
        var productList =  ArrayList<Product>()
        var category: Category

        for (categoryResponse in mainResponse.categoriesList!!) {
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
                    variant.size = if (variantResponse.size == null) "" else variantResponse.size as String

                    variantInfoList.add(variant)
                }

                product.variantInfo = variantInfoList

                var taxInfo = TaxInfo()
                taxInfo.name = productResponse.taxResponse?.name!!
                taxInfo.value = productResponse.taxResponse?.value!!
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
        for (rankingResponse in mainResponse.rankingsList!!) {

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
                    orderedRanking.order_count = viewCountResponse.orderCount!!
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



}