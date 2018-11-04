package com.softaai.heady_e_commerce.data.model.local

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.os.Parcel
import android.os.Parcelable


/**
 * Created by Amol Pawar on 04-11-2018.
 * softAai Apps
 */

@Entity(tableName = "product")
class Product() : Parcelable {

    @PrimaryKey
    var productId: Int = 0

    @ColumnInfo
    var categoryId: Int = 0

    @ColumnInfo
    var productName: String = ""

    @ColumnInfo
    var dataAdded: String = ""

    @ColumnInfo
    var variantInfo: ArrayList<VariantInfo> ? = null

    @ColumnInfo
    var taxInfo: TaxInfo ? = null

    constructor(parcel: Parcel) : this() {
        productId = parcel.readInt()
        categoryId = parcel.readInt()
        productName = parcel.readString()
        dataAdded = parcel.readString()
        taxInfo = parcel.readParcelable(TaxInfo.javaClass.classLoader)
        variantInfo = parcel.createTypedArrayList(VariantInfo.CREATOR)
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(productId)
        parcel.writeInt(categoryId)
        parcel.writeString(productName)
        parcel.writeString(dataAdded)
        parcel.writeParcelable(taxInfo, flags)
        parcel.writeTypedList(variantInfo)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Product> {
        override fun createFromParcel(parcel: Parcel): Product {
            return Product(parcel)
        }

        override fun newArray(size: Int): Array<Product?> {
            return arrayOfNulls(size)
        }
    }
}