package com.airy.juju.api

import androidx.lifecycle.LiveData
import com.airy.juju.bean.Activity
import com.airy.juju.bean.Group
import com.airy.juju.bean.Id
import com.airy.juju.bean.ListData
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import retrofit2.http.QueryMap



/**
 * Created by Airy on 2019/3/11
 * Mail: a532710813@gmail.com
 * Github: AiryMiku
 */

interface JuJuApi {


    // group
    @POST("/group/create/")
    fun createGroup(@QueryMap params: Map<String, Any>): Deferred<ReturnResult<Id>>

    @POST("/group/delete/")
    fun deleteGroup(@QueryMap params: Map<String, Any>): Deferred<ReturnResult<Any>>

    @POST("/group/modify/")
    fun modifyGroup(@QueryMap params: Map<String, Any>): Deferred<ReturnResult<Id>>

    @GET("/group/indexAll/")
    fun getGroups(@Query("page") page: Int, @Query("size") size: Int): Deferred<ReturnResult<ListData<Group>>>

    @GET("/group/baseInfo/")
    fun getGroupBaseInfo(@Query("group_id") id: Int): Deferred<ReturnResult<Group>>

    @GET("/group/baseActivityIndex/")
    fun getGroupBaseActivityIndex(@Query("group_id") id: Int,@Query("page") page: Int, @Query("size") size: Int): Deferred<ReturnResult<ListData<Activity>>>



    // activity
    @POST("/activity/create/")
    fun createActivity(@QueryMap params: Map<String, Any>): Deferred<ReturnResult<Id>>

    @POST("/activity/delete/")
    fun deleteActivity(@QueryMap params: Map<String, Any>): Deferred<ReturnResult<Any>>

    @POST("/activity/modify/")
    fun modifyActivity(@QueryMap params: Map<String, Any>): Deferred<ReturnResult<Id>>

    @GET("/activity/indexAll/")
    fun getActivities(@Query("page") page: Int, @Query("size") size: Int): Deferred<ReturnResult<ListData<Group>>>

    @GET("/activity/info/")
    fun getActivityInfo(@Query("activity_id") id: Int): Deferred<ReturnResult<Activity>>

    @POST("/activity/leaveComment/")
    fun leaveComment(@QueryMap params: Map<String, Any>): Deferred<ReturnResult<Any>>


    // search


    // playground

}