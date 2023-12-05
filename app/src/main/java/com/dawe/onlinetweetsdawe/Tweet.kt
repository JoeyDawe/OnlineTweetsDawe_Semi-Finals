package com.dawe.onlinetweetsdawe

import android.os.Parcel
import android.os.Parcelable

data class Tweet(
    val id: String,
    val text: String,
    val author: String,
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "taXVegwpotXTrNuQ4Cw8",
        parcel.readString() ?: "The quick brown fox jumps over the lazy dog.",
        parcel.readString() ?: "John Smith",
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(text)
        parcel.writeString(author)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Tweet> {
        override fun createFromParcel(parcel: Parcel): Tweet {
            return Tweet(parcel)
        }

        override fun newArray(size: Int): Array<Tweet?> {
            return arrayOfNulls(size)
        }
    }
}

data class TweetResponse(
    val message: String
)
