package com.airy.juju.bean

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable


/**
 * Created by Airy on 2019/4/3
 * Mail: a532710813@gmail.com
 * Github: AiryMiku
 */

data class IsOwner(val is_owner: Boolean) : Serializable, Parcelable {
    constructor(source: Parcel) : this(
        1 == source.readInt()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeInt((if (is_owner) 1 else 0))
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<IsOwner> = object : Parcelable.Creator<IsOwner> {
            override fun createFromParcel(source: Parcel): IsOwner = IsOwner(source)
            override fun newArray(size: Int): Array<IsOwner?> = arrayOfNulls(size)
        }
    }
}