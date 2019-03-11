package com.airy.juju.api

import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
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
            .baseUrl("http://127.0.0.1:8000")
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        @JvmField
        val juJuApi: JuJuApi = retrofit.create(JuJuApi::class.java)

        fun getBabyApi(): JuJuApi = juJuApi
    }

}