package com.airy.juju.api

import com.airy.juju.bean.Group
import com.airy.juju.bean.Id
import com.airy.juju.bean.ListData
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import retrofit2.http.QueryMap
import rx.Observable


/**
 * Created by Airy on 2019/3/11
 * Mail: a532710813@gmail.com
 * Github: AiryMiku
 */

interface JuJuApi {

    @POST("/group/create/")
    fun createGroup(@QueryMap params: Map<String, Any>): Observable<ReturnResult<Id>>

    @POST("/group/delete/")
    fun deleteGroup(@QueryMap params: Map<String, Any>): Observable<ReturnResult<Id>>

    @POST("/group/modify/")
    fun modifyGroup(@QueryMap params: Map<String, Any>): Observable<ReturnResult<Id>>

    @GET("/group/indexAll")
    fun getAllGroups(@Query("page") page: Int, @Query("size") size: Int): Observable<ReturnResult<ListData<Group>>>


}