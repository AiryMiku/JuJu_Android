package com.airy.juju.base

import android.widget.Toast
import androidx.fragment.app.Fragment


/**
 * Created by Airy on 2019/3/11
 * Mail: a532710813@gmail.com
 * Github: AiryMiku
 */

open class BaseFragment : Fragment() {

    fun makeToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}