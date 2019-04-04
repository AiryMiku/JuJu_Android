package com.airy.juju.bean

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable


/**
 * Created by Airy on 2019/4/4
 * Mail: a532710813@gmail.com
 * Github: AiryMiku
 */

data class IsEnableSearch(
    val is_enable_searched: Boolean
) : Serializable, Parcelable {
    constructor(source: Parcel) : this(
        1 == source.readInt()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeInt((if (is_enable_searched) 1 else 0))
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<IsEnableSearch> = object : Parcelable.Creator<IsEnableSearch> {
            override fun createFromParcel(source: Parcel): IsEnableSearch = IsEnableSearch(source)
            override fun newArray(size: Int): Array<IsEnableSearch?> = arrayOfNulls(size)
        }
    }
}