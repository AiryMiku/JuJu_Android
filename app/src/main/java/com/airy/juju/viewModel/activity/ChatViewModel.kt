package com.airy.juju.viewModel.activity

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.airy.juju.api.ReturnResult
import com.airy.juju.bean.ListData
import com.airy.juju.bean.Message
import com.airy.juju.bean.Session
import com.airy.juju.repository.ChatRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception


/**
 * Created by Airy on 2019/4/2
 * Mail: a532710813@gmail.com
 * Github: AiryMiku
 */

class ChatViewModel: ViewModel() {

   private val repository = ChatRepository.getInstance()

    val messages: MutableLiveData<ReturnResult<ListData<Message>>> = MutableLiveData()
    val postMessageResult: MutableLiveData<Boolean> = MutableLiveData()
    val session: MutableLiveData<ReturnResult<Session>> = MutableLiveData()

    fun getSession(params: Map<String, Any>) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val r = repository.getSession(params)
                withContext(Dispatchers.Main) {
                    session.value = r
                }
            }catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun getMessages(params: Map<String, Any>) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val r = repository.getSessionMessages(params)
                withContext(Dispatchers.Main) {
                    messages.value = r
                }
            }catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun postMessage(params: Map<String, Any>) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val r = repository.createMessage(params)
                withContext(Dispatchers.Main) {
                    postMessageResult.value = r.code == 0
                }
            }catch (e: Exception) {
                postMessageResult.value = false
                e.printStackTrace()
            }
        }
    }
}