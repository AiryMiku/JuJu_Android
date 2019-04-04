package com.airy.juju.repository

import com.airy.juju.api.RetrofitService
import com.airy.juju.api.ReturnResult
import com.airy.juju.bean.IsEnableSearch
import com.airy.juju.bean.PersonalPrivacy
import com.airy.juju.bean.Token
import com.airy.juju.bean.User


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
        return RetrofitService.getJuJuApi().userLoginAsync(params).await()
    }

    suspend fun signUp(params: Map<String, Any>): ReturnResult<Any> {
        return RetrofitService.getJuJuApi().userSignUpAsync(params).await()
    }

    suspend fun getUserInfo(userId: Int): ReturnResult<User> {
        return RetrofitService.getJuJuApi().getUserAsync(userId).await()
    }

    suspend fun modifyUserInfo(params: Map<String, Any>): ReturnResult<Any> {
        return RetrofitService.getJuJuApi().modifyUserInfoAsync(params).await()
    }

    suspend fun getPersonalPrivacy(params: Map<String, Any>): ReturnResult<PersonalPrivacy> {
        return RetrofitService.getJuJuApi().getPersonalPrivacyAsync(params).await()
    }

    suspend fun modifyPersonalPrivacy(params: Map<String, Any>): ReturnResult<Any> {
        return RetrofitService.getJuJuApi().modifyPersonalInfoPrivacyAsync(params).await()
    }

    suspend fun isEnableSearched(params: Map<String, Any>): ReturnResult<IsEnableSearch> {
        return RetrofitService.getJuJuApi().isEnableSearchAsync(params).await()
    }

    suspend fun modifyEnableSearched(params: Map<String, Any>): ReturnResult<Any> {
        return RetrofitService.getJuJuApi().enableOrDisableuserSearchPrivateAsync(params).await()
    }
}