package com.airy.juju.bean

import java.io.Serializable


/**
 * Created by Airy on 2019/3/12
 * Mail: a532710813@gmail.com
 * Github: AiryMiku
 */

data class activity(
    val id: Int,
    val title: String,
    val content: String,
    val place: String,
    val start_time: String,
    val end_time: String,
    val like_numer: Int
) : Serializable