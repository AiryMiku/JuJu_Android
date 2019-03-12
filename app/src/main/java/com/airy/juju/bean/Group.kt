package com.airy.juju.bean

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
    val create_time: String
) : Serializable