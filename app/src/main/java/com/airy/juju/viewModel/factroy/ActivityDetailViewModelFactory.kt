package com.airy.juju.viewModel.factroy

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.airy.juju.viewModel.activity.ActivityDetailViewModel


/**
 * Created by Airy on 2019/3/27
 * Mail: a532710813@gmail.com
 * Github: AiryMiku
 */

class ActivityDetailViewModelFactory(private val id: Int): ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ActivityDetailViewModel(id) as T
    }
}