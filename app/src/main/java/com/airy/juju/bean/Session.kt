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
    val id: Int,
    val type: Int,
    val left_id: Int,
    val right_id: Int,
    val content: String,
    val title: String,
    val latest_update_time: String
) : Serializable, Parcelable {
    constructor(source: Parcel) : this(
        source.readInt(),
        source.readInt(),
        source.readInt(),
        source.readInt(),
        source.readString(),
        source.readString(),
        source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeInt(id)
        writeInt(type)
        writeInt(left_id)
        writeInt(right_id)
        writeString(content)
        writeString(title)
        writeString(latest_update_time)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Session> = object : Parcelable.Creator<Session> {
            override fun createFromParcel(source: Parcel): Session = Session(source)
            override fun newArray(size: Int): Array<Session?> = arrayOfNulls(size)
        }
    }
}