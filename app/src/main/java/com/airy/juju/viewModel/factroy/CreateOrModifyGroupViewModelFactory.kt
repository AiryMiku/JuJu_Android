package com.airy.juju.viewModel.factroy

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.airy.juju.viewModel.activity.CreateOrModifyGroupViewModel


/**
 * Created by Airy on 2019/3/26
 * Mail: a532710813@gmail.com
 * Github: AiryMiku
 */

class CreateOrModifyGroupViewModelFactory(private val id: Int): ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CreateOrModifyGroupViewModel(id) as T
    }
}