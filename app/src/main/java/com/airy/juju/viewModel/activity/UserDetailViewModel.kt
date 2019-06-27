package com.airy.juju.viewModel.activity

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.airy.juju.api.ReturnResult
import com.airy.juju.bean.IsFollow
import com.airy.juju.bean.User
import com.airy.juju.repository.UserRepository
import com.airy.juju.util.UserCenter
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

class UserDetailViewModel(private val userId: Int): ViewModel() {

    private val repository = UserRepository.getInstance()

    val user: MutableLiveData<ReturnResult<User>> = MutableLiveData()
    val isFollowResult: MutableLiveData<ReturnResult<IsFollow>> = MutableLiveData()
    val followResult: MutableLiveData<Boolean> = MutableLiveData()
    val disFollowResult: MutableLiveData<Boolean> = MutableLiveData()

    init {
        refresh()
    }

    fun refresh() {
        fetchUserInfo()
        isFollow()
    }

    fun fetchUserInfo() {
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

    fun follow(params: Map<String, Any>) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val r = repository.followUser(params)
                withContext(Dispatchers.Main) {
                    followResult.value = r.code == 0
                }
            }catch (e : Exception) {
                withContext(Dispatchers.Main) {
                    followResult.value = false
                }
                e.printStackTrace()
            }
        }
    }

    fun disfollow(params: Map<String, Any>) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val r = repository.unfollowUser(params)
                withContext(Dispatchers.Main) {
                    disFollowResult.value = r.code == 0
                }
            }catch (e : Exception) {
                withContext(Dispatchers.Main) {
                    disFollowResult.value = false
                }
                e.printStackTrace()
            }
        }
    }

    fun isFollow() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val params = HashMap<String,Any>()
                params["user_id"] = userId
                params["access_token"] = UserCenter.getUserToken()
                val r = repository.isfollowUser(params)
                withContext(Dispatchers.Main) {
                    isFollowResult.value = r
                }
            }catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}