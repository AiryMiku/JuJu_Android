package com.airy.juju.ui.fragment.tab

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.airy.juju.api.ReturnResult
import com.airy.juju.bean.Activity
import com.airy.juju.bean.Group
import com.airy.juju.bean.ListData
import com.airy.juju.bean.User


/**
 * Created by Airy on 2019/3/30
 * Mail: a532710813@gmail.com
 * Github: AiryMiku
 */

class TabViewModel: ViewModel() {

    val activities: MutableLiveData<ReturnResult<ListData<Activity>>> = MutableLiveData()
    val groups: MutableLiveData<ReturnResult<ListData<Group>>> = MutableLiveData()
    val users: MutableLiveData<ReturnResult<ListData<User>>> = MutableLiveData()


    fun searchActivitys() {

    }

    fun searchGroups() {

    }

    fun searchUsers() {

    }




}