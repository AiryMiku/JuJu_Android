package com.airy.juju.bean

import android.os.Parcel
import android.os.Parcelable


/**
 * 用于接收返回的各种ID
 *
 * Created by Airy on 2019/3/12
 * Mail: a532710813@gmail.com
 * Github: AiryMiku
 */

data class Id(val id: Int) : Parcelable {
    constructor(source: Parcel) : this(
        source.readInt()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeInt(id)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Id> = object : Parcelable.Creator<Id> {
            override fun createFromParcel(source: Parcel): Id = Id(source)
            override fun newArray(size: Int): Array<Id?> = arrayOfNulls(size)
        }
    }
}