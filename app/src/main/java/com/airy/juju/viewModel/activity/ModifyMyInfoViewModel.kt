package com.airy.juju.viewModel.activity

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.airy.juju.api.ReturnResult
import com.airy.juju.bean.IsEnableSearch
import com.airy.juju.bean.PersonalPrivacy
import com.airy.juju.bean.User
import com.airy.juju.repository.UserRepository
import com.airy.juju.util.UserCenter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception


/**
 * Created by Airy on 2019/3/31
 * Mail: a532710813@gmail.com
 * Github: AiryMiku
 */

class ModifyMyInfoViewModel: ViewModel() {

    private val repository = UserRepository.getInstance()

    val user: MutableLiveData<ReturnResult<User>> = MutableLiveData()
    val modifyResult: MutableLiveData<Boolean> = MutableLiveData()
    val personalInfoPrivacy: MutableLiveData<ReturnResult<PersonalPrivacy>> = MutableLiveData()
    val enableSearch: MutableLiveData<ReturnResult<IsEnableSearch>> = MutableLiveData()
    val modifyPrivacyResult: MutableLiveData<Boolean> = MutableLiveData()
    val modifyEnableSearchResult: MutableLiveData<Boolean> = MutableLiveData()

    init {
        refresh()
    }

    fun refresh() {
        fetchUserInfo()
        fetchPersonalPrivacy()
        fetchEnableSearched()
    }

    fun fetchUserInfo() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val r = repository.getUserInfo(UserCenter.getUserId())
                withContext(Dispatchers.Main) {
                    user.value = r
                }
            }catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun modifyUserInfo(params: Map<String, Any>) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val r = repository.modifyUserInfo(params)
                withContext(Dispatchers.Main) {
                    modifyResult.value = r.code == 0
                }
            }catch (e: Exception) {
                e.printStackTrace()
                withContext(Dispatchers.Main) {
                    modifyResult.value = false
                }
            }
        }
    }

    fun fetchPersonalPrivacy() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val params = HashMap<String, Any>()
                params["access_token"] = UserCenter.getUserToken()
                val r = repository.getPersonalPrivacy(params)
                withContext(Dispatchers.Main) {
                    personalInfoPrivacy.value = r
                }
            }catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun fetchEnableSearched() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val params = HashMap<String, Any>()
                params["access_token"] = UserCenter.getUserToken()
                val r = repository.isEnableSearched(params)
                withContext(Dispatchers.Main) {
                    enableSearch.value = r
                }
            }catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun modifyPersonalPrivacy(params: Map<String, Any>) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val r = repository.modifyPersonalPrivacy(params)
                withContext(Dispatchers.Main) {
                    modifyPrivacyResult.value = r.code == 0
                }
            }catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    modifyPrivacyResult.value = false
                }
                e.printStackTrace()
            }
        }
    }

    fun modifyEnableSearch(params: Map<String, Any>) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val r = repository.modifyEnableSearched(params)
                withContext(Dispatchers.Main) {
                    modifyEnableSearchResult.value = r.code == 0
                }
            }catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    modifyEnableSearchResult.value = false
                }
                e.printStackTrace()
            }
        }
    }


}