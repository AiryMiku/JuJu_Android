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
        return RetrofitService.getJuJuApi().getActivityInfo(id).await()
    }

    suspend fun getActvities(page: Int, size: Int): ReturnResult<ListData<Activity>> {
        return RetrofitService.getJuJuApi().getActivities(page, size).await()
    }

    suspend fun getComments(id: Int, page: Int, size: Int): ReturnResult<ListData<Comment>> {
        return RetrofitService.getJuJuApi().getComments(id, page, size).await()
    }

    suspend fun createActivity(params: Map<String, Any>): ReturnResult<Id> {
        return RetrofitService.getJuJuApi().createActivity(params).await()
    }

    suspend fun modifyActivity(params: Map<String, Any>): ReturnResult<Any> {
        return RetrofitService.getJuJuApi().modifyActivity(params).await()
    }

    suspend fun deleteActivity(params: Map<String, Any>): ReturnResult<Any> {
        return RetrofitService.getJuJuApi().deleteActivity(params).await()
    }


    // todo

}