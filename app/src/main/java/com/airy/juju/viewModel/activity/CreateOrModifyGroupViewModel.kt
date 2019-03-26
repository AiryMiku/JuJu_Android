package com.airy.juju.viewModel.activity

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.airy.juju.api.ReturnResult
import com.airy.juju.bean.Activity
import com.airy.juju.bean.Group
import com.airy.juju.bean.ListData
import com.airy.juju.repository.GroupRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception


/**
 * Created by Airy on 2019/3/26
 * Mail: a532710813@gmail.com
 * Github: AiryMiku
 */

class CreateOrModifyGroupViewModel(private val groupId: Int)
    :ViewModel() {

    val group: MutableLiveData<ReturnResult<Group>> = MutableLiveData()
    val result: MutableLiveData<ReturnResult<Any>> = MutableLiveData()
    private val repository = GroupRepository.getInstance()

    init {
        refresh()
    }

    fun refresh() {
        fetchGroup()
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
            }
        }
    }

    fun modifyGroup(params: Map<String,Any>) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val r = repository.modifyGroup(params)
                withContext(Dispatchers.Main) {
                    result.value = r
                }
            }catch (e :Exception) {
                e.printStackTrace()
            }
        }
    }
}