package com.airy.juju.viewModel.activity

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.airy.juju.api.ReturnResult
import com.airy.juju.bean.*
import com.airy.juju.repository.GroupRepository
import com.airy.juju.util.UserCenter
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
    val deleteResult: MutableLiveData<Boolean> = MutableLiveData()
    val followResult: MutableLiveData<Boolean> = MutableLiveData()
    val disFollowResult: MutableLiveData<Boolean> = MutableLiveData()
    val isFollowResult: MutableLiveData<ReturnResult<IsFollow>> = MutableLiveData()

    init {
        refresh()
    }

    fun refresh() {
        fetchGroup()
        fetchGroupActivities()
        isFollow()
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

    fun deleteGroup(params: Map<String, Any>) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val r = repository.deleteGroup(params)
                withContext(Dispatchers.Main) {
                    deleteResult.value = r.code == 0
                }
            }catch (e :Exception) {
                withContext(Dispatchers.Main) {
                    deleteResult.value = false
                }
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
                    followResult.value = r.code == 0
                }
            }catch (e :Exception) {
                withContext(Dispatchers.Main) {
                    followResult.value = false
                }
                e.printStackTrace()
            }
        }
    }

    fun disfollow(params: Map<String, Any>){
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val r = repository.disfollowGroup(params)
                withContext(Dispatchers.Main) {
                    disFollowResult.value = r.code == 0
                }
            }catch (e :Exception) {
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
                val params = HashMap<String, Any>()
                params["group_id"] = groupId
                params["require_user_id"] = UserCenter.getUserId()
                val r = repository.isFollowGroup(params)
                withContext(Dispatchers.Main) {
                    isFollowResult.value = r
                }
            }catch (e :Exception) {
                e.printStackTrace()
            }
        }
    }



}