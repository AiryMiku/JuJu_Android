package com.airy.juju.viewModel.factroy

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.airy.juju.viewModel.activity.UserDetailViewModel


/**
 * Created by Airy on 2019/4/15
 * Mail: a532710813@gmail.com
 * Github: AiryMiku
 */

class UserDetailViewModelFactory (private val id: Int): ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return UserDetailViewModel(id) as T
    }
}