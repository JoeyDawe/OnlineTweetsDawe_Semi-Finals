package com.dawe.onlinetweetsdawe

import android.os.Parcel
import android.os.Parcelable

data class Timestamp(
    val seconds: Long,
    val nanoseconds: Long
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readLong(),
        parcel.readLong()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(seconds)
        parcel.writeLong(nanoseconds)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Timestamp> {
        override fun createFromParcel(parcel: Parcel): Timestamp {
            return Timestamp(parcel)
        }

        override fun newArray(size: Int): Array<Timestamp?> {
            return arrayOfNulls(size)
        }
    }
}
