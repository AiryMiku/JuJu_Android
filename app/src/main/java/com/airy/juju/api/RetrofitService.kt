package com.airy.juju.api

import android.util.Log
import com.airy.juju.Constant
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
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
        val loggingInterceptor: HttpLoggingInterceptor = HttpLoggingInterceptor {
            Log.e("RetrofitLog", it)
        }.setLevel(HttpLoggingInterceptor.Level.BODY)

        @JvmField
        val loginClient: OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

        @JvmField
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(Constant.Server.BASE_URL)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .client(loginClient)    // add log
            .build()

        @JvmField
        val juJuApi: JuJuApi = retrofit.create(JuJuApi::class.java)

        fun getJuJuApi(): JuJuApi = juJuApi
    }

}