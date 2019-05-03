package com.airy.juju


/**
 * Created by Airy on 2019/3/28
 * Mail: a532710813@gmail.com
 * Github: AiryMiku
 */

object Common {

    object SharedPreferenceKey {
        const val USER_ID: String = "USERID"
        const val ACCESS_TOKEN: String = "ACCESSTOKEN"
    }

    object ActivityCreateOrModifyKey {
        const val TYPE_KEY: String = "TYPE_KEY"
        const val CREATE_KEY: String = "CREATE_KEY"
        const val MODIFY_KEY: String = "MODIFY_KEY"
    }

    object ParamTranferKey {
        const val GROUP_ID_KEY: String = "GROUP_ID_KEY"
        const val ACTIVITY_ID_KEY: String = "ACTIVITY_ID_KEY"
        const val USER_ID_KEY = "USER_ID_KEY"
        const val SESSION_ID_KEY = "SESSION_ID_KEY"
    }

    object ItemListTypeKey {
        const val TYPE_KEY: String = "TYPE_KEY"
        const val MY_GROUP: String = "MY_GROUP"
        const val MY_ACTIVITY: String = "MY_ACTIVITY"
        const val MY_FOLLOW_USER: String = "MY_FOLLOW_USER"
        const val USER: String = "USER"
    }

    object SearchKey {
        const val TYPE_KEY: String = "TYPE_KEY"
        const val KEY_WORD: String = "KEY_WORD"
        const val GROUP: String = "GROUP"
        const val ACTIVITY: String = "ACTIVITY"
        const val USER: String = "USER"
    }

    object MainActivityFragmentKey {
        const val PLAYGROUND: String = "PLAYGROUND"
        const val GROUP: String = "GROUP"
        const val NOTIFICATION: String = "NOTIFICATION"
        const val CHAT: String = "CHAT"
        const val ME: String = "ME"
    }

    object ChatEnterType {
        const val KEY = "CHAT_ENTER_TYPE_KEY"
        const val FROM_SESSION_LIST = "FROM_SESSION_LIST"
        const val FROM_MESSAGE_BUTTON = "FROM_MESSAGE_BUTTON"
        const val FROM_GROUP = "FROM_GROUP"
    }

}