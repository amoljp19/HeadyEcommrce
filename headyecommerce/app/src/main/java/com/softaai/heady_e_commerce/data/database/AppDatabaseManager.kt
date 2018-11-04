package com.softaai.heady_e_commerce.data.database

import com.softaai.heady_e_commerce.model.remote.CategoryResponse
import com.softaai.heady_e_commerce.model.remote.CategoryProductResponse
import com.softaai.heady_e_commerce.model.remote.MainResponse


/**
 * Created by Amol Pawar on 04-11-2018.
 * softAai Apps
 */
class AppDatabaseManager {

    fun insertAllDataInDB(mainResponse: MainResponse) {
        var categoryList = ArrayList<CategoryResponse>()
        var productList =  ArrayList<CategoryProductResponse>()
        var categoryResponse: CategoryResponse

        for (categoryResponse in mainResponse.categoriesList!!) {
            categoryResponse = CategoryResponse()
            categoryResponse = CategoryResponse()
            categoryResponse.categoryId = categoryResponse.id!!
            categoryResponse.categoryName = categoryResponse.name!!

            var product: Product
            for (productResponse in categoryResponse.products!!) {
                product = Product()
                product.categoryId = categoryResponse.id!!
                product.productId = productResponse.id!!
                product.dataAdded = productResponse.dateAdded!!
                product.productName = productResponse.name!!

                var variant: VariantInfo
                var variantInfoList = ArrayList<VariantInfo>()
                for (variantResponse in productResponse.variants!!) {
                    variant = VariantInfo()
                    variant.id = variantResponse.id as Int
                    variant.color = variantResponse.color as String
                    variant.price = variantResponse.price as Int
                    variant.size = if (variantResponse.size == null) "" else variantResponse.size as String

                    variantInfoList.add(variant)
                }

                product.variantInfo = variantInfoList

                var taxInfo = TaxInfo()
                taxInfo.name = productResponse.tax?.name!!
                taxInfo.value = productResponse.tax?.value!!
                product.taxInfo  = taxInfo

                productList.add(product)
            }

            if (productList.size > 0) {
                categoryList.add(categoryResponse)
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
        for (rankingResponse in apiResponse.rankings!!) {

            if ("Most Viewed Products".equals(rankingResponse.ranking)) {
                for (viewCountResponse in rankingResponse.products!!) {
                    var viewedRanking = ViewedRanking()
                    viewedRanking.id = viewCountResponse.id!!
                    viewedRanking.view_count = viewCountResponse.viewCount!!
                    viewedRankingList.add(viewedRanking)
                }
            } else if ("Most OrdeRed Products".equals(rankingResponse.ranking)) {
                for (viewCountResponse in rankingResponse.products!!) {
                    var orderedRanking = OrderedRanking()
                    orderedRanking.id = viewCountResponse.id!!
                    orderedRanking.order_count = viewCountResponse.orderCount!!
                    orderedRankingList.add(orderedRanking)
                }
            } else if ("Most ShaRed Products".equals(rankingResponse.ranking)) {
                for (viewCountResponse in rankingResponse.products!!) {
                    var sharedRanking = SharedRanking()
                    sharedRanking.id = viewCountResponse.id!!
                    sharedRanking.shares = viewCountResponse.shares!!
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