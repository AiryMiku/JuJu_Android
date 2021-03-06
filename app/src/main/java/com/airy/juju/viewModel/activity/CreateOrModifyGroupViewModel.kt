package com.airy.juju.viewModel.activity

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.airy.juju.api.ReturnResult
import com.airy.juju.bean.Group
import com.airy.juju.bean.Id
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
    val modifyResult: MutableLiveData<ReturnResult<Any>> = MutableLiveData()
    val createResult: MutableLiveData<ReturnResult<Id>> = MutableLiveData()
    private val repository = GroupRepository.getInstance()

    init {

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

    fun modifyGroup(params: Map<String,Any>) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val r = repository.modifyGroup(params)
                withContext(Dispatchers.Main) {
                    modifyResult.value = r
                }
            }catch (e :Exception) {
                e.printStackTrace()
            }
        }
    }


}