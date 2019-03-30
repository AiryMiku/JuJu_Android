package com.airy.juju


/**
 * Created by Airy on 2019/3/28
 * Mail: a532710813@gmail.com
 * Github: AiryMiku
 */

object Common {

    object SharedPreferenceKey {
        const val USER_ID = "USERID"
        const val ACCESS_TOKEN = "ACCESSTOKEN"
    }

    object ActivityCreateOrModifyKey {
        const val TYPE_KEY: String = "TYPE_KEY"
        const val CREATE_KEY: String = "CREATE_KEY"
        const val MODIFY_KEY: String = "MODIFY_KEY"
    }

    object ParamTranferKey {
        const val GROUP_ID_KEY = "GROUP_ID_KEY"
        const val ACTIVITY_ID_KEY = "ACTIVITY_ID_KEY"
    }

    object ItemListTypeKey {
        const val TYPE_KEY: String = "TYPE_KEY"
        const val MY_GROUP = "MY_GROUP"
        const val MY_ACTIVITY = "MY_ACTIVITY"
    }

    object SearchKey {
        const val TYPE_KEY: String = "TYPE_KEY"
        const val KEY_WORD = "KEY_WORD"
        const val GROUP = "GROUP"
        const val ACTIVITY = "ACTIVITY"
        const val USER = "USER"
    }

}