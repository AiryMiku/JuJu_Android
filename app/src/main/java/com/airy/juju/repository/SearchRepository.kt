package com.airy.juju.repository

import com.airy.juju.api.RetrofitService
import com.airy.juju.api.ReturnResult
import com.airy.juju.bean.Activity
import com.airy.juju.bean.Group
import com.airy.juju.bean.ListData
import com.airy.juju.bean.User


/**
 * Created by Airy on 2019/3/27
 * Mail: a532710813@gmail.com
 * Github: AiryMiku
 */

class SearchRepository{

    companion object {
        @Volatile
        private var instance: SearchRepository? = null

        fun getInstance(): SearchRepository = instance ?: synchronized(this) {
            instance ?: SearchRepository().also { instance = it }
        }
    }

    suspend fun getSearchActivities(keyWord: String, page: Int, size: Int): ReturnResult<ListData<Activity>> {
        return RetrofitService.getJuJuApi().searchActivityAsync(keyWord, page, size).await()
    }

    suspend fun getSearchGroups(keyWord: String, page: Int, size: Int): ReturnResult<ListData<Group>> {
        return RetrofitService.getJuJuApi().searchGroupAsync(keyWord, page, size).await()
    }

    suspend fun getSearchUsers(keyWord: String, page: Int, size: Int): ReturnResult<ListData<User>> {
        return RetrofitService.getJuJuApi().searchUserAsync(keyWord, page, size).await()
    }
}