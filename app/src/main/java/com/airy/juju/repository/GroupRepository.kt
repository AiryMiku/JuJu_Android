package com.airy.juju.repository

import com.airy.juju.api.RetrofitService
import com.airy.juju.api.ReturnResult
import com.airy.juju.bean.Group
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

    //...Todo
}