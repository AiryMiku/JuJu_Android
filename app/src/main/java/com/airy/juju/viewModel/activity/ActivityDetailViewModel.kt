package com.airy.juju.viewModel.activity

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.airy.juju.api.ReturnResult
import com.airy.juju.bean.Activity
import com.airy.juju.bean.Comment
import com.airy.juju.bean.IsFollow
import com.airy.juju.bean.ListData
import com.airy.juju.repository.ActivityRepository
import com.airy.juju.util.UserCenter
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
    val commentResult: MutableLiveData<Boolean> = MutableLiveData()
    val followResult: MutableLiveData<Boolean> = MutableLiveData()
    val disFollowResult: MutableLiveData<Boolean> = MutableLiveData()
    val isFollowResult: MutableLiveData<ReturnResult<IsFollow>> = MutableLiveData()

    companion object {
        val TAG = "ActivityDetailViewModel"
    }

    init {
        refresh()
    }

    fun refresh() {
        fetchActivity()
        fetchComments()
        isFollow()
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

    fun leaveComment(params: Map<String, Any>) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val r = repository.leaveComment(params)
                withContext(Dispatchers.Main) {
                    commentResult.value = r.code == 0
                }
            }catch (e : Exception) {
                withContext(Dispatchers.Main) {
                    commentResult.value = false
                }
                e.printStackTrace()
            }
        }
    }

    fun follow(params: Map<String, Any>) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val r = repository.followActivity(params)
                withContext(Dispatchers.Main) {
                    followResult.value = r.code == 0
                }
            }catch (e : Exception) {
                withContext(Dispatchers.Main) {
                    followResult.value = false
                }
                e.printStackTrace()
            }
        }
    }

    fun disfollow(params: Map<String, Any>) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val r = repository.disFollowActivity(params)
                withContext(Dispatchers.Main) {
                    disFollowResult.value = r.code == 0
                }
            }catch (e : Exception) {
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
                params["activity_id"] = activityId
                params["require_user_id"] = UserCenter.getUserId()
                val r = repository.isFollowActivity(params)
                withContext(Dispatchers.Main) {
                    isFollowResult.value = r
                }
            }catch (e : Exception) {
                e.printStackTrace()
            }
        }
    }


}