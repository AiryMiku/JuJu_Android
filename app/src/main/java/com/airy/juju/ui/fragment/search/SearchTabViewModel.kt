package com.airy.juju.ui.fragment.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.airy.juju.api.ReturnResult
import com.airy.juju.bean.Activity
import com.airy.juju.bean.Group
import com.airy.juju.bean.ListData
import com.airy.juju.bean.User
import com.airy.juju.repository.SearchRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception


/**
 * Created by Airy on 2019/3/30
 * Mail: a532710813@gmail.com
 * Github: AiryMiku
 */

class SearchTabViewModel: ViewModel() {

    val activities: MutableLiveData<ReturnResult<ListData<Activity>>> = MutableLiveData()
    val groups: MutableLiveData<ReturnResult<ListData<Group>>> = MutableLiveData()
    val users: MutableLiveData<ReturnResult<ListData<User>>> = MutableLiveData()
    private val repository = SearchRepository.getInstance()

    fun searchActivitys(keyword: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val r = repository.getSearchActivities(keyword, 1, 99)
                withContext(Dispatchers.Main) {
                    activities.value = r
                }
            }catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun searchGroups(keyword: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val r = repository.getSearchGroups(keyword, 1, 99)
                withContext(Dispatchers.Main) {
                    groups.value = r
                }
            }catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun searchUsers(keyword: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val r = repository.getSearchUsers(keyword, 1, 99)
                withContext(Dispatchers.Main) {
                    users.value = r
                }
            }catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }




}