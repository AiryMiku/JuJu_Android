package com.airy.juju.viewModel.activity

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

class GroupDetailViewModel(val group_id: Int) :ViewModel() {

    val group: MutableLiveData<ReturnResult<Group>> = MutableLiveData()
    val groupActivities: MutableLiveData<ReturnResult<ListData<Activity>>> = MutableLiveData()
    val repository = GroupRepository.getInstance()

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
                val r = repository.getGroupBaseInfo(group_id)
                withContext(Dispatchers.Main) {
                    group.value = r
                }
            }catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun fetchGroupActivities() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val r = repository.getGroupBaseActivityIndex(group_id, 1, 99)
                withContext(Dispatchers.Main) {
                    groupActivities.value = r
                }
            }catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}