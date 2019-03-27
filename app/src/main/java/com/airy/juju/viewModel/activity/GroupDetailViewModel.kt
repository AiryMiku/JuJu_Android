package com.airy.juju.viewModel.activity

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.airy.juju.api.ReturnResult
import com.airy.juju.bean.Activity
import com.airy.juju.bean.Group
import com.airy.juju.bean.Id
import com.airy.juju.bean.ListData
import com.airy.juju.repository.GroupRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception


/**
 * Created by Airy on 2019/3/24
 * Mail: a532710813@gmail.com
 * Github: AiryMiku
 */

class GroupDetailViewModel(private val groupId: Int) :ViewModel() {

    companion object {
        val TAG = "GroupDetailViewModel"
    }

    private val repository = GroupRepository.getInstance()

    val group: MutableLiveData<ReturnResult<Group>> = MutableLiveData()
    val groupActivities: MutableLiveData<ReturnResult<ListData<Activity>>> = MutableLiveData()
    val deleteResult: MutableLiveData<ReturnResult<Any>> = MutableLiveData()
    val createResult: MutableLiveData<ReturnResult<Id>> = MutableLiveData()
    val followResult: MutableLiveData<Any> = MutableLiveData()

    init {
        refresh()
    }

    fun refresh() {
        fetchGroup()
        fetchGroupActivities()
    }

    fun fetchGroup() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val r = repository.getGroupBaseInfo(groupId)
                withContext(Dispatchers.Main) {
                    group.value = r
                }
            }catch (e: Exception) {
                e.printStackTrace()
                Log.e(TAG, e.message)
            }
        }
    }

    fun fetchGroupActivities() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val r = repository.getGroupBaseActivityIndex(groupId, 1, 99)
                withContext(Dispatchers.Main) {
                    groupActivities.value = r
                }
            }catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun createGroup(params: Map<String, Any>) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val r = repository.createGroup(params)
                withContext(Dispatchers.Main) {
                    createResult.value = r
                }
            }catch (e :Exception) {
                e.printStackTrace()
            }
        }
    }

    fun deleteGroup(params: Map<String, Any>) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val r = repository.deleteGroup(params)
                withContext(Dispatchers.Main) {
                    deleteResult.value = r
                }
            }catch (e :Exception) {
                e.printStackTrace()
            }
        }
    }

    fun setAdmin() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                withContext(Dispatchers.Main) {

                }
            }catch (e :Exception) {
                e.printStackTrace()
                Log.e(TAG, e.message)
            }
        }
    }

    fun removeMember() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                withContext(Dispatchers.Main) {

                }
            }catch (e :Exception) {
                e.printStackTrace()
            }
        }
    }

    fun follow(params: Map<String, Any>) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val r = repository.followGroup(params)
                withContext(Dispatchers.Main) {
                    followResult.value = r
                }
            }catch (e :Exception) {
                e.printStackTrace()
            }
        }
    }

    fun disfollow(params: Map<String, Any>){
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val r = repository.disfollowGroup(params)
                withContext(Dispatchers.Main) {
                    followResult.value = r
                }
            }catch (e :Exception) {
                e.printStackTrace()
            }
        }
    }



}