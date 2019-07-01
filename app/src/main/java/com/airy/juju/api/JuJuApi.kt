package com.airy.juju.api

import com.airy.juju.bean.*
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
    suspend fun createGroupAsync(@FieldMap params: Map<String, Any>): ReturnResult<Id>

    @FormUrlEncoded
    @POST("/group/delete/")
    suspend fun deleteGroupAsync(@FieldMap params: Map<String, Any>): ReturnResult<Any>

    @FormUrlEncoded
    @POST("/group/modify/")
    suspend fun modifyGroupAsync(@FieldMap params: Map<String, Any>): ReturnResult<Any>

    @GET("/group/indexAll/")
    suspend fun getGroupsAsync(@Query("page") page: Int,
                       @Query("size") size: Int): ReturnResult<ListData<Group>>

    @GET("/group/baseInfo/")
    suspend fun getGroupBaseInfoAsync(@Query("group_id") id: Int): ReturnResult<Group>

    @GET("/group/baseActivityIndex/")
    suspend fun getGroupBaseActivityIndexAsync(@Query("group_id") id: Int,
                                       @Query("page") page: Int,
                                       @Query("size") size: Int): ReturnResult<ListData<Activity>>

    @FormUrlEncoded
    @POST("/group/memberIndex/")
    suspend fun getGroupMembersIndexAsync(@FieldMap params: Map<String, Any>): ReturnResult<ListData<User>>

    @FormUrlEncoded
    @POST("/group/indexFollow/")
    suspend fun getFollowGroupsAsync(@FieldMap params: Map<String, Any>): ReturnResult<ListData<Group>>

    @FormUrlEncoded
    @POST("/group/follow/")
    suspend fun followGroupAsync(@FieldMap params: Map<String, Any>): ReturnResult<Any>

    @FormUrlEncoded
    @POST("/group/disFollow/")
    suspend fun unfollowGroupAsync(@FieldMap params: Map<String, Any>): ReturnResult<Any>

    @FormUrlEncoded
    @POST("/group/isFollow/")
    suspend fun isFollowGroupAsync(@FieldMap params: Map<String, Any>): ReturnResult<IsFollow>

    @FormUrlEncoded
    @POST("/group/remove_member/")
    suspend fun removeGroupMemberAsync(@FieldMap params: Map<String, Any>): ReturnResult<Any>

    // activity
    @FormUrlEncoded
    @POST("/activity/create/")
    suspend fun createActivityAsync(@FieldMap params: Map<String, Any>): ReturnResult<Id>

    @FormUrlEncoded
    @POST("/activity/delete/")
    suspend fun deleteActivityAsync(@FieldMap params: Map<String, Any>): ReturnResult<Any>

    @FormUrlEncoded
    @POST("/activity/modify/")
    suspend fun modifyActivityAsync(@FieldMap params: Map<String, Any>): ReturnResult<Any>

    @GET("/activity/indexAll/")
    suspend fun getActivitiesAsync(@Query("page") page: Int,
                           @Query("size") size: Int): ReturnResult<ListData<Activity>>

    @FormUrlEncoded
    @POST("/activity/indexAttend/")
    suspend fun getAttendActivitiesAsync(@FieldMap params: Map<String, Any>): ReturnResult<ListData<Activity>>

    @GET("/activity/info/")
    suspend fun getActivityInfoAsync(@Query("activity_id") id: Int): ReturnResult<Activity>

    @FormUrlEncoded
    @POST("/activity/leaveComment/")
    suspend fun leaveCommentAsync(@FieldMap params: Map<String, Any>): ReturnResult<Any>

    @FormUrlEncoded
    @POST("/activity/deleteComment/")
    suspend fun deleteCommentAsync(@FieldMap params: Map<String, Any>): ReturnResult<Any>

    @GET("/activity/indexComment/")
    suspend fun getCommentsAsync(@Query("activity_id") id: Int,
                         @Query("page") page: Int,
                         @Query("size") size: Int): ReturnResult<ListData<Comment>>

    @FormUrlEncoded
    @POST("activity/follow/")
    suspend fun followActivityAsync(@FieldMap params: Map<String, Any>): ReturnResult<Any>

    @FormUrlEncoded
    @POST("activity/disFollow/")
    suspend fun unFollowActivityAsync(@FieldMap params: Map<String, Any>): ReturnResult<Any>

    @FormUrlEncoded
    @POST("activity/isFollow/")
    suspend fun isFollowActivityAsync(@FieldMap params: Map<String, Any>): ReturnResult<IsFollow>

    @FormUrlEncoded
    @POST("activity/like/")
    suspend fun likeActivityAsync(@FieldMap params: Map<String, Any>): ReturnResult<Any>

    @FormUrlEncoded
    @POST("activity/dislike/")
    suspend fun dislikeActivityAsync(@FieldMap params: Map<String, Any>): ReturnResult<Any>

    // search
    @GET("/search/activity/")
    suspend fun searchActivityAsync(@Query("key_word") key_word: String,
                            @Query("page") page: Int,
                            @Query("size") size: Int): ReturnResult<ListData<Activity>>

    @GET("/search/group/")
    suspend fun searchGroupAsync(@Query("key_word") key_word: String,
                         @Query("page") page: Int,
                         @Query("size") size: Int): ReturnResult<ListData<Group>>

    @GET("/search/user")
    suspend fun searchUserAsync(@Query("key_word") key_word: String,
                        @Query("page") page: Int,
                        @Query("size") size: Int): ReturnResult<ListData<User>>

    // playground
    @GET("/playground/show/")
    suspend fun getPlaygroundAsync(@Query("page") page: Int,
                           @Query("size") size: Int): ReturnResult<ListData<Activity>>

    // user
    @FormUrlEncoded
    @POST("/user/login/")
    suspend fun userLoginAsync(@FieldMap params: Map<String, Any>): ReturnResult<Token>

    @FormUrlEncoded
    @POST("/user/register/")
    suspend fun userSignUpAsync(@FieldMap params: Map<String, Any>): ReturnResult<Any>

    @FormUrlEncoded
    @POST("/user/modify_password/")
    suspend fun changePasswordAsync(@FieldMap params: Map<String, Any>): ReturnResult<Any>

    @GET("/user/get_information_by_id/")
    suspend fun getUserAsync(@Query("user_id") userId: Int): ReturnResult<User>

    @FormUrlEncoded
    @POST("/user/is_admin/")
    suspend fun isGroupAdminAsync(@FieldMap params: Map<String, Any>): ReturnResult<Any>

    @FormUrlEncoded
    @POST("/user/follow/")
    suspend fun followUserAsync(@FieldMap params: Map<String, Any>): ReturnResult<Any>

    @FormUrlEncoded
    @POST("/user/dis_follow/")
    suspend fun unFollowUserAsync(@FieldMap params: Map<String, Any>): ReturnResult<Any>

    @FormUrlEncoded
    @POST("/user/is_follow/")
    suspend fun isFollowUserAsync(@FieldMap params: Map<String, Any>): ReturnResult<IsFollow>

    @FormUrlEncoded
    @POST("/user/modify_information/")
    suspend fun modifyUserInfoAsync(@FieldMap params: Map<String, Any>): ReturnResult<Any>

    @FormUrlEncoded
    @POST("/user/is_enable_searched/")
    suspend fun isEnableSearchAsync(@FieldMap params: Map<String, Any>): ReturnResult<IsEnableSearch>

    @FormUrlEncoded
    @POST("/user/modify_enable_searched/")
    suspend fun enableOrDisableuserSearchPrivateAsync(@FieldMap params: Map<String, Any>): ReturnResult<Any> // ture or false

    @FormUrlEncoded
    @POST("/user/modify_enable_visited_list/")
    suspend fun modifyPersonalInfoPrivacyAsync(@FieldMap params: Map<String, Any>): ReturnResult<Any>

    @FormUrlEncoded
    @POST("/user/get_enable_visited_list/")
    suspend fun getPersonalPrivacyAsync(@FieldMap params: Map<String, Any>): ReturnResult<PersonalPrivacy>

    @FormUrlEncoded
    @POST("/user/get_follow_list/")
    suspend fun getFollowUsersAsync(@FieldMap params: Map<String, Any>): ReturnResult<ListData<User>>

    // message
    @FormUrlEncoded
    @POST("/message/get_session/")
    suspend fun getSessionAsync(@FieldMap params: Map<String, Any>): ReturnResult<Session>

    @FormUrlEncoded
    @POST("/message/get_session_by_id/")
    suspend fun getSessionByIdAsync(@FieldMap params: Map<String, Any>): ReturnResult<Session>

    @FormUrlEncoded
    @POST("/message/create_message/")
    suspend fun createMessageAsync(@FieldMap params: Map<String, Any>): ReturnResult<Any>

    @FormUrlEncoded
    @POST("/message/get_message_list_by_session_id/")
    suspend fun getSessionMessagesAsync(@FieldMap params: Map<String, Any>): ReturnResult<ListData<Message>>

    @FormUrlEncoded
    @POST("/message/get_session_list/")
    suspend fun getSessionsAsync(@FieldMap params: Map<String, Any>): ReturnResult<ListData<Session>>

    // notification
    @FormUrlEncoded
    @POST("/notification/get_notification_list/")
    suspend fun getNotificationsAsync(@FieldMap params: Map<String, Any>): ReturnResult<ListData<Notification>>
}