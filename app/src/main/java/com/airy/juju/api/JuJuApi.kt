package com.airy.juju.api

import androidx.lifecycle.LiveData
import com.airy.juju.bean.*
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
    fun createGroupAsync(@FieldMap params: Map<String, Any>): Deferred<ReturnResult<Id>>

    @FormUrlEncoded
    @POST("/group/delete/")
    fun deleteGroupAsync(@FieldMap params: Map<String, Any>): Deferred<ReturnResult<Any>>

    @FormUrlEncoded
    @POST("/group/modify/")
    fun modifyGroupAsync(@FieldMap params: Map<String, Any>): Deferred<ReturnResult<Any>>

    @GET("/group/indexAll/")
    fun getGroupsAsync(@Query("page") page: Int, @Query("size") size: Int): Deferred<ReturnResult<ListData<Group>>>

    @GET("/group/baseInfo/")
    fun getGroupBaseInfoAsync(@Query("group_id") id: Int): Deferred<ReturnResult<Group>>

    @GET("/group/baseActivityIndex/")
    fun getGroupBaseActivityIndexAsync(@Query("group_id") id: Int, @Query("page") page: Int, @Query("size") size: Int): Deferred<ReturnResult<ListData<Activity>>>

    @GET("/group/memberIndex/")
    fun getGroupMembersIndexAsync(@Query("group_id") id: Int, @Query("page") page: Int, @Query("size") size: Int): Deferred<ReturnResult<ListData<Group>>>

    @FormUrlEncoded
    @POST("/group/indexFollow/")
    fun getFollowGroupsAsync(@FieldMap params: Map<String, Any>): Deferred<ReturnResult<ListData<Group>>>

    @FormUrlEncoded
    @POST("/group/follow/")
    fun followGroupAsync(@FieldMap params: Map<String, Any>): Deferred<ReturnResult<Any>>

    @FormUrlEncoded
    @POST("/group/disFollow/")
    fun disfollowGroupAsync(@FieldMap params: Map<String, Any>): Deferred<ReturnResult<Any>>

    @FormUrlEncoded
    @POST("/group/isFollow/")
    fun isFollowGroupAsync(@FieldMap params: Map<String, Any>): Deferred<ReturnResult<IsFollow>>

    // activity
    @FormUrlEncoded
    @POST("/activity/create/")
    fun createActivityAsync(@FieldMap params: Map<String, Any>): Deferred<ReturnResult<Id>>

    @FormUrlEncoded
    @POST("/activity/delete/")
    fun deleteActivityAsync(@FieldMap params: Map<String, Any>): Deferred<ReturnResult<Any>>

    @FormUrlEncoded
    @POST("/activity/modify/")
    fun modifyActivityAsync(@FieldMap params: Map<String, Any>): Deferred<ReturnResult<Any>>

    @GET("/activity/indexAll/")
    fun getActivitiesAsync(@Query("page") page: Int, @Query("size") size: Int): Deferred<ReturnResult<ListData<Activity>>>

    @FormUrlEncoded
    @POST("/activity/indexAttend/")
    fun getAttendActivitiesAsync(@FieldMap params: Map<String, Any>): Deferred<ReturnResult<ListData<Activity>>>

    @GET("/activity/info/")
    fun getActivityInfoAsync(@Query("activity_id") id: Int): Deferred<ReturnResult<Activity>>

    @FormUrlEncoded
    @POST("/activity/leaveComment/")
    fun leaveCommentAsync(@FieldMap params: Map<String, Any>): Deferred<ReturnResult<Any>>

    @FormUrlEncoded
    @POST("/activity/deleteComment/")
    fun deleteCommentAsync(@FieldMap params: Map<String, Any>): Deferred<ReturnResult<Any>>

    @GET("/activity/indexComment/")
    fun getCommentsAsync(@Query("activity_id") id: Int, @Query("page") page: Int, @Query("size") size: Int): Deferred<ReturnResult<ListData<Comment>>>

    // search
    @GET("/search/activity/")
    fun searchActivityAsync(@Query("key_word") key_word: String,
                            @Query("page") page: Int,
                            @Query("size") size: Int): Deferred<ReturnResult<ListData<Activity>>>

    @GET("/search/")
    fun searchGroupAsync(@Query("key_word") key_word: String,
                         @Query("page") page: Int,
                         @Query("size") size: Int): Deferred<ReturnResult<ListData<Group>>>

    @GET("/search/user")
    fun searchUserAsync(@Query("key_word") key_word: String,
                        @Query("page") page: Int,
                        @Query("size") size: Int): Deferred<ReturnResult<ListData<User>>>

    // playground
    @GET("/playground/show/")
    fun getPlaygroundAsync(@Query("key_word") key_word: String,
                           @Query("page") page: Int,
                           @Query("size") size: Int): Deferred<ReturnResult<ListData<Activity>>>

    // user
    @FormUrlEncoded
    @POST("/user/login/")
    fun userLoginAsync(@FieldMap params: Map<String, Any>): Deferred<ReturnResult<Token>>

    @FormUrlEncoded
    @POST("/user/register/")
    fun userSignUpAsync(@FieldMap params: Map<String, Any>): Deferred<ReturnResult<Any>>


}