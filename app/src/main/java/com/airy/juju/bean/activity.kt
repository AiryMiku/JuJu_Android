package com.airy.juju.bean

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable


/**
 * Created by Airy on 2019/3/12
 * Mail: a532710813@gmail.com
 * Github: AiryMiku
 */

data class Activity(
    val id: Int,
    val title: String,
    val content: String,
    val place: String,
    val start_time: String,
    val end_time: String,
    val like_number: Int
) : Serializable, Parcelable {
    constructor(source: Parcel) : this(
        source.readInt(),
        source.readString(),
        source.readString(),
        source.readString(),
        source.readString(),
        source.readString(),
        source.readInt()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeInt(id)
        writeString(title)
        writeString(content)
        writeString(place)
        writeString(start_time)
        writeString(end_time)
        writeInt(like_number)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Activity> = object : Parcelable.Creator<Activity> {
            override fun createFromParcel(source: Parcel): Activity = Activity(source)
            override fun newArray(size: Int): Array<Activity?> = arrayOfNulls(size)
        }
    }
}