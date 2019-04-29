package com.airy.juju.repository

import com.airy.juju.api.RetrofitService
import com.airy.juju.api.ReturnResult
import com.airy.juju.bean.*


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
        return RetrofitService.getJuJuApi().getGroupsAsync(page, size).await()
    }

    suspend fun getGroupBaseInfo(id: Int) : ReturnResult<Group> {
        return RetrofitService.getJuJuApi().getGroupBaseInfoAsync(id).await()
    }

    suspend fun getGroupBaseActivityIndex(id: Int, page: Int, size: Int) : ReturnResult<ListData<Activity>> {
        return RetrofitService.getJuJuApi().getGroupBaseActivityIndexAsync(id, page, size).await()
    }

    suspend fun getFollowGroup(params: Map<String, Any>): ReturnResult<ListData<Group>> {
        return RetrofitService.getJuJuApi().getFollowGroupsAsync(params).await()
    }

    suspend fun getGroupMembers(params: Map<String, Any>): ReturnResult<ListData<User>> {
        return RetrofitService.getJuJuApi().getGroupMembersIndexAsync(params).await()
    }

    suspend fun createGroup(params: Map<String, Any>) :ReturnResult<Id> {
        return RetrofitService.getJuJuApi().createGroupAsync(params).await()
    }

    suspend fun modifyGroup(params: Map<String,Any>) :ReturnResult<Any> {
        return RetrofitService.getJuJuApi().modifyGroupAsync(params).await()
    }

    suspend fun deleteGroup(params: Map<String, Any>) :ReturnResult<Any> {
        return RetrofitService.getJuJuApi().deleteGroupAsync(params).await()
    }

    suspend fun followGroup(params: Map<String, Any>) :ReturnResult<Any> {
        return RetrofitService.getJuJuApi().followGroupAsync(params).await()
    }

    suspend fun disfollowGroup(params: Map<String, Any>) :ReturnResult<Any> {
        return RetrofitService.getJuJuApi().disfollowGroupAsync(params).await()
    }

    suspend fun isFollowGroup(params: Map<String, Any>): ReturnResult<IsFollow> {
        return RetrofitService.getJuJuApi().isFollowGroupAsync(params).await()
    }

    suspend fun removeGroupMember(params: Map<String, Any>): ReturnResult<Any> {
        return RetrofitService.getJuJuApi().removeGroupMemberAsync(params).await()
    }
}