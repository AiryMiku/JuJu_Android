package com.airy.juju.ui.fragment.chat

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.airy.juju.api.ReturnResult
import com.airy.juju.bean.ListData
import com.airy.juju.bean.Session
import com.airy.juju.repository.ChatRepository
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

class ChatViewModel : ViewModel() {

    val repository = ChatRepository.getInstance()

    val sessions: MutableLiveData<ReturnResult<ListData<Session>>> = MutableLiveData()

    init {
        getSessions()
    }

    fun getSessions() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val params = HashMap<String, Any>()
                params["access_token"] = UserCenter.getUserToken()
                params["page"] = 1
                params["size"] = 99
                val r = repository.getSessions(params)
                withContext(Dispatchers.Main) {
                    sessions.value = r
                }
            }catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}