package com.airy.juju.bean

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable


/**
 * Created by Airy on 2019/3/28
 * Mail: a532710813@gmail.com
 * Github: AiryMiku
 */

data class Notification(
    val id: Int,
    val notification_type: String,
    val notification_content: String,
    val to_id: Int,
    val create_time: String
) : Serializable, Parcelable {
    constructor(source: Parcel) : this(
        source.readInt(),
        source.readString(),
        source.readString(),
        source.readInt(),
        source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeInt(id)
        writeString(notification_type)
        writeString(notification_content)
        writeInt(to_id)
        writeString(create_time)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Notification> = object : Parcelable.Creator<Notification> {
            override fun createFromParcel(source: Parcel): Notification = Notification(source)
            override fun newArray(size: Int): Array<Notification?> = arrayOfNulls(size)
        }
    }
}