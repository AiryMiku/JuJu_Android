package com.airy.juju.ui.fragment.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.airy.juju.api.ReturnResult
import com.airy.juju.bean.Group
import com.airy.juju.bean.ListData
import com.airy.juju.repository.GroupRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject


/**
 * Created by Airy on 2019/3/14
 * Mail: a532710813@gmail.com
 * Github: AiryMiku
 */

class HomeViewModel
//@Inject constructor(repository: GroupRepository) 以后再进行ioc
    : ViewModel() {

    // put your data here
    val groups: MutableLiveData<ReturnResult<ListData<Group>>> = MutableLiveData()
    val repository = GroupRepository.getInstance()

    init {
        // when using ui to fetch data
        fetchGroups()
    }

    fun refresh() {
        fetchGroups()
    }

    fun fetchGroups() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val r = repository.getAllGroups()
                withContext(Dispatchers.Main) {
                    groups.value = r
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}