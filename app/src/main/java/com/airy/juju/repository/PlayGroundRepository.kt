package com.airy.juju.repository

import com.airy.juju.api.RetrofitService
import com.airy.juju.api.ReturnResult
import com.airy.juju.bean.Activity
import com.airy.juju.bean.ListData


/**
 * Created by Airy on 2019/3/27
 * Mail: a532710813@gmail.com
 * Github: AiryMiku
 */

class PlayGroundRepository{
    companion object {
        @Volatile
        private var instance: PlayGroundRepository? = null

        fun getInstance(): PlayGroundRepository = instance ?: synchronized(this) {
            instance ?: PlayGroundRepository().also { instance = it }
        }
    }

    suspend fun getPlayGround(page: Int, size: Int): ReturnResult<ListData<Activity>> {
        return RetrofitService.getJuJuApi().getPlaygroundAsync(page, size).await()
    }
}