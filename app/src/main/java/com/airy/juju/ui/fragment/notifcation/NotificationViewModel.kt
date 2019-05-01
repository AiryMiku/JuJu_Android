package com.airy.juju.ui.fragment.notifcation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.airy.juju.api.ReturnResult
import com.airy.juju.bean.ListData
import com.airy.juju.bean.Notification
import com.airy.juju.repository.NotificationRepository
import com.airy.juju.util.UserCenter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception


/**
 * Created by Airy on 2019/3/21
 * Mail: a532710813@gmail.com
 * Github: AiryMiku
 */

class NotificationViewModel
    :ViewModel() {

    val repository = NotificationRepository.getInstance()
    val notifications: MutableLiveData<ReturnResult<ListData<Notification>>> = MutableLiveData()

    init {
        fetchNotifications()
    }

    fun refresh() {
        fetchNotifications()
    }

    fun fetchNotifications() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val params = HashMap<String, Any>()
                params["access_token"] = UserCenter.getUserToken()
                params["page"] = 1
                params["size"] = 99
                val r = repository.getNotifications(params)
                withContext(Dispatchers.Main) {
                    notifications.value = r
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
    }

}