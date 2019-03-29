package com.airy.juju.bean

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable


/**
 * Created by Airy on 2019/3/29
 * Mail: a532710813@gmail.com
 * Github: AiryMiku
 */

data class Session(
    val id: Int
) : Serializable, Parcelable {
    constructor(source: Parcel) : this(
        source.readInt()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeInt(id)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Session> = object : Parcelable.Creator<Session> {
            override fun createFromParcel(source: Parcel): Session = Session(source)
            override fun newArray(size: Int): Array<Session?> = arrayOfNulls(size)
        }
    }
}