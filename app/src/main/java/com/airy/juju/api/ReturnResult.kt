package com.airy.juju.api


/**
 * Created by Airy on 2019/3/11
 * Mail: a532710813@gmail.com
 * Github: AiryMiku
 */

data class ReturnResult<T>(val code: Int, val msg: String, val data: T)