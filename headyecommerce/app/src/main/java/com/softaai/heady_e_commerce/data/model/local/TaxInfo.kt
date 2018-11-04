package com.softaai.heady_e_commerce.data.model.local

import android.os.Parcel
import android.os.Parcelable


/**
 * Created by Amol Pawar on 04-11-2018.
 * softAai Apps
 */

class TaxInfo() : Parcelable {

    var name: String = ""
    var value : Float = 0f

    constructor(parcel: Parcel) : this() {
        name = parcel.readString()
        value = parcel.readFloat()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeFloat(value)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<TaxInfo> {
        override fun createFromParcel(parcel: Parcel): TaxInfo {
            return TaxInfo(parcel)
        }

        override fun newArray(size: Int): Array<TaxInfo?> {
            return arrayOfNulls(size)
        }
    }

}