package com.airy.juju.bean

import java.io.Serializable


/**
 * Created by Airy on 2019/3/12
 * Mail: a532710813@gmail.com
 * Github: AiryMiku
 */

data class ListData<T>(
    val count: Int,
    val list: List<T>
) : Serializable