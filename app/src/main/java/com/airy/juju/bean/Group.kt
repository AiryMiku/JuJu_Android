package com.airy.juju.bean

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable


/**
 * Created by Airy on 2019/3/12
 * Mail: a532710813@gmail.com
 * Github: AiryMiku
 */

data class Group(
    val id: Int,
    val owner_user_id: Int,
    val name: String,
    val notice: String,
    val introduction: String,
    val create_time: String,
    var is_follow: Boolean
) : Serializable, Parcelable {
    constructor(source: Parcel) : this(
        source.readInt(),
        source.readInt(),
        source.readString(),
        source.readString(),
        source.readString(),
        source.readString(),
        1 == source.readInt()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeInt(id)
        writeInt(owner_user_id)
        writeString(name)
        writeString(notice)
        writeString(introduction)
        writeString(create_time)
        writeInt((if (is_follow) 1 else 0))
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Group> = object : Parcelable.Creator<Group> {
            override fun createFromParcel(source: Parcel): Group = Group(source)
            override fun newArray(size: Int): Array<Group?> = arrayOfNulls(size)
        }
    }
}