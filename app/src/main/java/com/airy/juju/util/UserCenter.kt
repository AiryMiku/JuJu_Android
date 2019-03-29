package com.airy.juju.util

import android.content.Context
import com.airy.juju.Common
import com.airy.juju.Common.SharedPreferenceKey.USER_ID
import com.airy.juju.Common.SharedPreferenceKey.ACCESS_TOKEN
import com.airy.juju.JuJuApplication.Companion.getApplicationContext


/**
 * Created by Airy on 2019/3/28
 * Mail: a532710813@gmail.com
 * Github: AiryMiku
 */

fun isLogin(context: Context): Boolean {
    return getSp(context).getString(Common.SharedPreferenceKey.ACCESS_TOKEN, "")?.isNotBlank() ?: false
}

fun getUserId(): String = getSp(getApplicationContext()).getString(USER_ID, "") ?: ""

fun getUserToken(): String = getSp(getApplicationContext()).getString(ACCESS_TOKEN, "") ?: ""

fun logout() {
    getSp(getApplicationContext()).edit().putString(Common.SharedPreferenceKey.USER_ID, "").apply()
    getSp(getApplicationContext()).edit().putString(Common.SharedPreferenceKey.ACCESS_TOKEN, "").apply()
}

fun saveLogin(context: Context, userId: String, userToken: String) {
    getSp(context).edit().putString(Common.SharedPreferenceKey.ACCESS_TOKEN, userToken).apply()
    getSp(context).edit().putString(Common.SharedPreferenceKey.USER_ID, userId).apply()
}