package com.airy.juju.repository

import com.airy.juju.api.RetrofitService
import com.airy.juju.api.ReturnResult
import com.airy.juju.bean.*


/**
 * Created by Airy on 2019/3/27
 * Mail: a532710813@gmail.com
 * Github: AiryMiku
 */

class UserRepository{

    companion object {
        @Volatile
        private var instance: UserRepository? = null

        fun getInstance(): UserRepository = instance ?: synchronized(this) {
            instance ?: UserRepository().also { instance = it }
        }
    }

    suspend fun login(params: Map<String, Any>): ReturnResult<Token> {
        return RetrofitService.getJuJuApi().userLoginAsync(params)
    }

    suspend fun signUp(params: Map<String, Any>): ReturnResult<Any> {
        return RetrofitService.getJuJuApi().userSignUpAsync(params)
    }

    suspend fun getUserInfo(userId: Int): ReturnResult<User> {
        return RetrofitService.getJuJuApi().getUserAsync(userId)
    }

    suspend fun modifyUserInfo(params: Map<String, Any>): ReturnResult<Any> {
        return RetrofitService.getJuJuApi().modifyUserInfoAsync(params)
    }

    suspend fun getPersonalPrivacy(params: Map<String, Any>): ReturnResult<PersonalPrivacy> {
        return RetrofitService.getJuJuApi().getPersonalPrivacyAsync(params)
    }

    suspend fun modifyPersonalPrivacy(params: Map<String, Any>): ReturnResult<Any> {
        return RetrofitService.getJuJuApi().modifyPersonalInfoPrivacyAsync(params)
    }

    suspend fun isEnableSearched(params: Map<String, Any>): ReturnResult<IsEnableSearch> {
        return RetrofitService.getJuJuApi().isEnableSearchAsync(params)
    }

    suspend fun modifyEnableSearched(params: Map<String, Any>): ReturnResult<Any> {
        return RetrofitService.getJuJuApi().enableOrDisableuserSearchPrivateAsync(params)
    }

    suspend fun followUser(params: Map<String, Any>): ReturnResult<Any> {
        return RetrofitService.getJuJuApi().followUserAsync(params)
    }

    suspend fun unfollowUser(params: Map<String, Any>): ReturnResult<Any> {
        return RetrofitService.getJuJuApi().unFollowUserAsync(params)
    }

    suspend fun isfollowUser(params: Map<String, Any>): ReturnResult<IsFollow> {
        return RetrofitService.getJuJuApi().isFollowUserAsync(params)
    }

    suspend fun getFollowUsers(params: Map<String, Any>): ReturnResult<ListData<User>> {
        return RetrofitService.getJuJuApi().getFollowUsersAsync(params)
    }

    suspend fun changePassword(params: Map<String, Any>): ReturnResult<Any> {
        return RetrofitService.getJuJuApi().changePasswordAsync(params)
    }
}