package com.airy.juju.repository

import com.airy.juju.api.RetrofitService
import com.airy.juju.api.ReturnResult
import com.airy.juju.bean.ListData
import com.airy.juju.bean.Notification


/**
 * Created by Airy on 2019/3/27
 * Mail: a532710813@gmail.com
 * Github: AiryMiku
 */

class NotificationRepository {
    companion object {
        @Volatile
        private var instance: NotificationRepository? = null

        fun getInstance(): NotificationRepository = instance ?: synchronized(this) {
            instance ?: NotificationRepository().also { instance = it }
        }
    }

    suspend fun getNotifications(params: Map<String, Any>): ReturnResult<ListData<Notification>> {
        return RetrofitService.getJuJuApi().getNotificationsAsync(params)
    }
}