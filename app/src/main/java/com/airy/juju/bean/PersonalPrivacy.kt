package com.airy.juju.bean

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable


/**
 * Created by Airy on 2019/4/3
 * Mail: a532710813@gmail.com
 * Github: AiryMiku
 */

data class PersonalPrivacy(
    val birth: Int,
    val phone: Int,
    val status: Int
) : Serializable, Parcelable {
    constructor(source: Parcel) : this(
        source.readInt(),
        source.readInt(),
        source.readInt()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeInt(birth)
        writeInt(phone)
        writeInt(status)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<PersonalPrivacy> = object : Parcelable.Creator<PersonalPrivacy> {
            override fun createFromParcel(source: Parcel): PersonalPrivacy = PersonalPrivacy(source)
            override fun newArray(size: Int): Array<PersonalPrivacy?> = arrayOfNulls(size)
        }
    }
}