package com.airy.juju.repository

import com.airy.juju.api.RetrofitService
import com.airy.juju.api.ReturnResult
import com.airy.juju.bean.ListData
import com.airy.juju.bean.Message
import com.airy.juju.bean.Session


/**
 * Created by Airy on 2019/3/27
 * Mail: a532710813@gmail.com
 * Github: AiryMiku
 */

class ChatRepository {

    companion object {
        @Volatile
        private var instance: ChatRepository? = null

        fun getInstance(): ChatRepository = instance ?: synchronized(this) {
            instance ?: ChatRepository().also { instance = it }
        }
    }

    suspend fun getSession(params: Map<String, Any>): ReturnResult<Session> {
        return RetrofitService.getJuJuApi().getSessionAsync(params).await()
    }

    suspend fun getSessions(params: Map<String, Any>): ReturnResult<ListData<Session>> {
        return RetrofitService.getJuJuApi().getSessionsAsync(params).await()
    }

    suspend fun createMessage(params: Map<String, Any>): ReturnResult<Any> {
        return RetrofitService.getJuJuApi().createMessageAsync(params).await()
    }

    suspend fun getSessionMessages(params: Map<String, Any>): ReturnResult<ListData<Message>> {
        return RetrofitService.getJuJuApi().getSessionMessagesAsync(params).await()
    }

    suspend fun getSessionById(params: Map<String, Any>): ReturnResult<Session> {
        return RetrofitService.getJuJuApi().getSessionByIdAsync(params).await()
    }

}