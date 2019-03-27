package com.airy.juju.viewModel.activity

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.airy.juju.api.ReturnResult
import com.airy.juju.bean.Activity
import com.airy.juju.bean.Comment
import com.airy.juju.bean.ListData
import com.airy.juju.repository.ActivityRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception


/**
 * Created by Airy on 2019/3/27
 * Mail: a532710813@gmail.com
 * Github: AiryMiku
 */

class ActivityDetailViewModel(private val activityId: Int) :ViewModel() {

    private val repository = ActivityRepository.getInstance()

    val activity: MutableLiveData<ReturnResult<Activity>> = MutableLiveData()
    val comments: MutableLiveData<ReturnResult<ListData<Comment>>> = MutableLiveData()

    companion object {
        val TAG = "ActivityDetailViewModel"
    }

    init {
        refresh()
    }

    fun refresh() {
        fetchActivity()
        fetchComments()
    }

    fun fetchActivity() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val r = repository.getActivity(activityId)
                withContext(Dispatchers.Main) {
                    activity.value = r
                }
            }catch (e : Exception) {
                e.printStackTrace()
            }
        }
    }

    fun fetchComments() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val r = repository.getComments(activityId, 1, 99)
                withContext(Dispatchers.Main) {
                    comments.value = r
                }
            }catch (e : Exception) {
                e.printStackTrace()
            }
        }
    }


}