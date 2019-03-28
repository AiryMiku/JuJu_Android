package com.airy.juju.util

import android.content.Context
import android.preference.PreferenceManager


/**
 * Created by Airy on 2019/3/28
 * Mail: a532710813@gmail.com
 * Github: AiryMiku
 */


fun getSp(context: Context) = PreferenceManager.getDefaultSharedPreferences(context)