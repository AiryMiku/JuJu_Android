package com.airy.juju.bean

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable


/**
 * Created by Airy on 2019/3/30
 * Mail: a532710813@gmail.com
 * Github: AiryMiku
 */

data class Token(
    val token: String
) : Serializable, Parcelable {
    constructor(source: Parcel) : this(
        source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(token)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Token> = object : Parcelable.Creator<Token> {
            override fun createFromParcel(source: Parcel): Token = Token(source)
            override fun newArray(size: Int): Array<Token?> = arrayOfNulls(size)
        }
    }
}