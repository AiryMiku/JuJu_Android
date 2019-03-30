package com.airy.juju.repository

import com.airy.juju.api.RetrofitService
import com.airy.juju.api.ReturnResult
import com.airy.juju.bean.Activity
import com.airy.juju.bean.Comment
import com.airy.juju.bean.Id
import com.airy.juju.bean.ListData


/**
 * Created by Airy on 2019/3/27
 * Mail: a532710813@gmail.com
 * Github: AiryMiku
 */

class ActivityRepository {

    companion object {
        @Volatile
        private var instance: ActivityRepository? = null

        fun getInstance(): ActivityRepository = instance ?: synchronized(this) {
            instance ?: ActivityRepository().also { instance = it }
        }
    }

    suspend fun getActivity(id: Int): ReturnResult<Activity> {
        return RetrofitService.getJuJuApi().getActivityInfoAsync(id).await()
    }

    suspend fun getActvities(page: Int, size: Int): ReturnResult<ListData<Activity>> {
        return RetrofitService.getJuJuApi().getActivitiesAsync(page, size).await()
    }

    suspend fun getAttendActivities(params: Map<String, Any>): ReturnResult<ListData<Activity>> {
        return RetrofitService.getJuJuApi().getAttendActivitiesAsync(params).await()
    }

    suspend fun getComments(id: Int, page: Int, size: Int): ReturnResult<ListData<Comment>> {
        return RetrofitService.getJuJuApi().getCommentsAsync(id, page, size).await()
    }

    suspend fun createActivity(params: Map<String, Any>): ReturnResult<Id> {
        return RetrofitService.getJuJuApi().createActivityAsync(params).await()
    }

    suspend fun modifyActivity(params: Map<String, Any>): ReturnResult<Any> {
        return RetrofitService.getJuJuApi().modifyActivityAsync(params).await()
    }

    suspend fun deleteActivity(params: Map<String, Any>): ReturnResult<Any> {
        return RetrofitService.getJuJuApi().deleteActivityAsync(params).await()
    }


    // todo

}