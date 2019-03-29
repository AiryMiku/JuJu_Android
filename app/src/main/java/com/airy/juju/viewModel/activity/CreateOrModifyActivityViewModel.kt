package com.airy.juju.viewModel.activity

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.airy.juju.api.ReturnResult
import com.airy.juju.bean.Activity
import com.airy.juju.bean.Id
import com.airy.juju.repository.ActivityRepository
import kotlinx.coroutines.*
import java.lang.Exception


/**
 * Created by Airy on 2019/3/29
 * Mail: a532710813@gmail.com
 * Github: AiryMiku
 */

class CreateOrModifyActivityViewModel(private val activityId: Int): ViewModel() {

    val activity: MutableLiveData<ReturnResult<Activity>> = MutableLiveData()
    val modifyResult: MutableLiveData<ReturnResult<Any>> = MutableLiveData()
    val createResult: MutableLiveData<ReturnResult<Id>> = MutableLiveData()
    val repository = ActivityRepository.getInstance()

    init {

    }

    fun refresh() {
        fetchActivity()
    }

    fun fetchActivity() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val r = repository.getActivity(activityId)
                withContext(Dispatchers.Main) {
                    activity.value = r
                }
            }catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun createActivity(params: Map<String, Any>) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val r = repository.createActivity(params)
                withContext(Dispatchers.Main) {
                    createResult.value = r
                }
            }catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun modifyActivity(params: Map<String, Any>) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val r = repository.modifyActivity(params)
                withContext(Dispatchers.Main) {
                    modifyResult.value = r
                }
            }catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}