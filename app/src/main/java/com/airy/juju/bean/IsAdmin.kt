package com.airy.juju.bean

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable


/**
 * Created by Airy on 2019/4/3
 * Mail: a532710813@gmail.com
 * Github: AiryMiku
 */

data class IsAdmin(val is_admin: Boolean) : Serializable, Parcelable {
    constructor(source: Parcel) : this(
        1 == source.readInt()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeInt((if (is_admin) 1 else 0))
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<IsAdmin> = object : Parcelable.Creator<IsAdmin> {
            override fun createFromParcel(source: Parcel): IsAdmin = IsAdmin(source)
            override fun newArray(size: Int): Array<IsAdmin?> = arrayOfNulls(size)
        }
    }
}