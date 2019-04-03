package com.airy.juju.api

import com.airy.juju.Constant
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * Created by Airy on 2019/3/11
 * Mail: a532710813@gmail.com
 * Github: AiryMiku
 */

class RetrofitService {

    companion object {

        @JvmField
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(Constant.Server.BASE_URL)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        @JvmField
        val juJuApi: JuJuApi = retrofit.create(JuJuApi::class.java)

        fun getJuJuApi(): JuJuApi = juJuApi
    }

}