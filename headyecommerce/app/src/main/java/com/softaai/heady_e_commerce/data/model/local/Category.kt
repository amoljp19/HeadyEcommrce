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

@Entity(tableName = "category")
class Category() : Parcelable {

    @PrimaryKey
    var categoryId: Int = 0

    @ColumnInfo
    var categoryName: String = ""

    constructor(parcel: Parcel) : this() {
        categoryId = parcel.readInt()
        categoryName = parcel.readString()
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeInt(categoryId)
        dest.writeString(categoryName)
    }

    companion object CREATOR : Parcelable.Creator<Category> {
        override fun createFromParcel(`in`: Parcel): Category {
            return Category(`in`)
        }

        override fun newArray(size: Int): Array<Category?> {
            return arrayOfNulls(size)
        }
    }
}