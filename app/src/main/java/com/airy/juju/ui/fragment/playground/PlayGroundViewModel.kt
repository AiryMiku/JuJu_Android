package com.airy.juju.ui.fragment.playground

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.airy.juju.api.ReturnResult
import com.airy.juju.bean.Activity
import com.airy.juju.bean.ListData
import com.airy.juju.repository.PlayGroundRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception


/**
 * Created by Airy on 2019/4/1
 * Mail: a532710813@gmail.com
 * Github: AiryMiku
 */

class PlayGroundViewModel: ViewModel() {

    private val repository = PlayGroundRepository.getInstance()
    val activities: MutableLiveData<ReturnResult<ListData<Activity>>> = MutableLiveData()

    init {
        refresh()
    }

    fun refresh() {
        fetchPlayGround()
    }

    fun fetchPlayGround() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val r = repository.getPlayGround(1, 99)
                withContext(Dispatchers.Main) {
                    activities.value = r
                }
            }catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}