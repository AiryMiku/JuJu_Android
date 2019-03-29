package com.airy.juju.bean

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable


/**
 * Created by Airy on 2019/3/29
 * Mail: a532710813@gmail.com
 * Github: AiryMiku
 */

data class IsFollow(val is_follow: Boolean) : Serializable, Parcelable {
    constructor(source: Parcel) : this(
        1 == source.readInt()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeInt((if (is_follow) 1 else 0))
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<IsFollow> = object : Parcelable.Creator<IsFollow> {
            override fun createFromParcel(source: Parcel): IsFollow = IsFollow(source)
            override fun newArray(size: Int): Array<IsFollow?> = arrayOfNulls(size)
        }
    }
}