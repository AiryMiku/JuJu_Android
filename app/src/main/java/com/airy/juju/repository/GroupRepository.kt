package com.airy.juju.repository

import com.airy.juju.api.RetrofitService
import com.airy.juju.api.ReturnResult
import com.airy.juju.bean.Activity
import com.airy.juju.bean.Group
import com.airy.juju.bean.Id
import com.airy.juju.bean.ListData


/**
 * Created by Airy on 2019/3/13
 * Mail: a532710813@gmail.com
 * Github: AiryMiku
 */

//@Singleton
class GroupRepository {

    companion object {
        @Volatile
        private var instance: GroupRepository? = null

        fun getInstance(): GroupRepository = instance ?: synchronized(this) {
            instance ?: GroupRepository().also { instance = it }
        }
    }

    suspend fun getGroups(page: Int, size: Int) : ReturnResult<ListData<Group>> {
        return RetrofitService.getJuJuApi().getGroups(page, size).await()
    }

    suspend fun getGroupBaseInfo(id: Int) : ReturnResult<Group> {
        return RetrofitService.getJuJuApi().getGroupBaseInfo(id).await()
    }

    suspend fun getGroupBaseActivityIndex(id: Int, page: Int, size: Int) : ReturnResult<ListData<Activity>> {
        return RetrofitService.getJuJuApi().getGroupBaseActivityIndex(id, page, size).await()
    }

    suspend fun createGroup(params: Map<String, Any>) :ReturnResult<Id> {
        return RetrofitService.getJuJuApi().createGroup(params).await()
    }

    suspend fun modifyGroup(params: Map<String,Any>) :ReturnResult<Any> {
        return RetrofitService.getJuJuApi().modifyGroup(params).await()
    }

    suspend fun deleteGroup(params: Map<String, Any>) :ReturnResult<Any> {
        return RetrofitService.getJuJuApi().deleteGroup(params).await()
    }

    suspend fun followGroup(params: Map<String, Any>) :ReturnResult<Any> {
        return RetrofitService.getJuJuApi().followGroup(params).await()
    }

    suspend fun disfollowGroup(params: Map<String, Any>) :ReturnResult<Any> {
        return RetrofitService.getJuJuApi().disfollowGroup(params).await()
    }

    //...Todo
}