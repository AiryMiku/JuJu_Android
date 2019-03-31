package com.airy.juju.viewModel.activity

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.airy.juju.api.ReturnResult
import com.airy.juju.bean.Activity
import com.airy.juju.bean.Group
import com.airy.juju.bean.ListData
import com.airy.juju.bean.User
import com.airy.juju.repository.ActivityRepository
import com.airy.juju.repository.GroupRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception
import kotlin.contracts.contract


/**
 * Created by Airy on 2019/3/30
 * Mail: a532710813@gmail.com
 * Github: AiryMiku
 */

class ItemListViewModel: ViewModel() {

    val activities: MutableLiveData<ReturnResult<ListData<Activity>>> = MutableLiveData()
    val groups: MutableLiveData<ReturnResult<ListData<Group>>> = MutableLiveData()
    val users: MutableLiveData<ReturnResult<ListData<User>>> = MutableLiveData()

    val groupRepository = GroupRepository.getInstance()
    val activityRepository = ActivityRepository.getInstance()

    fun fetchFollowGroups(params: Map<String, Any>) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val r = groupRepository.getFollowGroup(params)
                withContext(Dispatchers.Main) {
                    groups.value = r
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun fetchActivity(params: Map<String, Any>) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val r = activityRepository.getAttendActivities(params)
                withContext(Dispatchers.Main) {
                    activities.value = r
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun fetchMemberInGroup(params: Map<String, Any>) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val r = groupRepository.getGroupMembers(params)
                withContext(Dispatchers.Main) {
                    users.value = r
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}