package com.airy.juju.api

import androidx.lifecycle.LiveData
import com.airy.juju.bean.Activity
import com.airy.juju.bean.Group
import com.airy.juju.bean.Id
import com.airy.juju.bean.ListData
import kotlinx.coroutines.Deferred
import retrofit2.http.*


/**
 * Created by Airy on 2019/3/11
 * Mail: a532710813@gmail.com
 * Github: AiryMiku
 */

@JvmSuppressWildcards // Map<String, Any> it be compiled into [java.util.Map<java.lang.String, ?>]
interface JuJuApi {


    // group
    @FormUrlEncoded
    @POST("/group/create/")
    fun createGroup(@FieldMap params: Map<String, Any>): Deferred<ReturnResult<Id>>

    @FormUrlEncoded
    @POST("/group/delete/")
    fun deleteGroup(@FieldMap params: Map<String, Any>): Deferred<ReturnResult<Any>>

    @FormUrlEncoded
    @POST("/group/modify/")
    fun modifyGroup(@FieldMap params: Map<String, Any>): Deferred<ReturnResult<Any>>

    @GET("/group/indexAll/")
    fun getGroups(@Query("page") page: Int, @Query("size") size: Int): Deferred<ReturnResult<ListData<Group>>>

    @GET("/group/baseInfo/")
    fun getGroupBaseInfo(@Query("group_id") id: Int): Deferred<ReturnResult<Group>>

    @GET("/group/baseActivityIndex/")
    fun getGroupBaseActivityIndex(@Query("group_id") id: Int,@Query("page") page: Int, @Query("size") size: Int): Deferred<ReturnResult<ListData<Activity>>>


    // activity
    @FormUrlEncoded
    @POST("/activity/create/")
    fun createActivity(@FieldMap params: Map<String, Any>): Deferred<ReturnResult<Id>>

    @FormUrlEncoded
    @POST("/activity/delete/")
    fun deleteActivity(@FieldMap params: Map<String, Any>): Deferred<ReturnResult<Any>>

    @FormUrlEncoded
    @POST("/activity/modify/")
    fun modifyActivity(@FieldMap params: Map<String, Any>): Deferred<ReturnResult<Id>>

    @GET("/activity/indexAll/")
    fun getActivities(@Query("page") page: Int, @Query("size") size: Int): Deferred<ReturnResult<ListData<Group>>>

    @GET("/activity/info/")
    fun getActivityInfo(@Query("activity_id") id: Int): Deferred<ReturnResult<Activity>>

    @FormUrlEncoded
    @POST("/activity/leaveComment/")
    fun leaveComment(@FieldMap params: Map<String, Any>): Deferred<ReturnResult<Any>>


    // search


    // playground

}