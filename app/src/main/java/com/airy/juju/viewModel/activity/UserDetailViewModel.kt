package com.airy.juju.viewModel.activity

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.airy.juju.api.ReturnResult
import com.airy.juju.bean.User
import com.airy.juju.repository.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception


/**
 * Created by Airy on 2019/4/1
 * Mail: a532710813@gmail.com
 * Github: AiryMiku
 */

class UserDetailViewModel: ViewModel() {

    private val repository = UserRepository.getInstance()

    val user: MutableLiveData<ReturnResult<User>> = MutableLiveData()

    init {
        refresh()
    }

    fun refresh() {
//        fetchUserInfo()
    }

    fun fetchUserInfo(userId: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val r = repository.getUserInfo(userId)
                withContext(Dispatchers.Main) {
                    user.value = r
                }
            }catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}