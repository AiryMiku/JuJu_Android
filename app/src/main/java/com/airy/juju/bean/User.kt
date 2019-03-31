package com.airy.juju.bean

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable


/**
 * Created by Airy on 2019/3/25
 * Mail: a532710813@gmail.com
 * Github: AiryMiku
 */

data class User(
    val id: Int,
    val nickname: String,
    val sex: Int,
    val birth: String,
    val phone: String,
    val status: String
) : Serializable, Parcelable {
    constructor(source: Parcel) : this(
        source.readInt(),
        source.readString(),
        source.readInt(),
        source.readString(),
        source.readString(),
        source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeInt(id)
        writeString(nickname)
        writeInt(sex)
        writeString(birth)
        writeString(phone)
        writeString(status)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<User> = object : Parcelable.Creator<User> {
            override fun createFromParcel(source: Parcel): User = User(source)
            override fun newArray(size: Int): Array<User?> = arrayOfNulls(size)
        }
    }
}