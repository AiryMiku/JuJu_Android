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
    val access_token: String,
    val user_id: Int
) : Serializable, Parcelable {
    constructor(source: Parcel) : this(
        source.readString(),
        source.readInt()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(access_token)
        writeInt(user_id)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Token> = object : Parcelable.Creator<Token> {
            override fun createFromParcel(source: Parcel): Token = Token(source)
            override fun newArray(size: Int): Array<Token?> = arrayOfNulls(size)
        }
    }
}