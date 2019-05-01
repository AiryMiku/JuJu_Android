package com.airy.juju.viewModel.activity

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.airy.juju.repository.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception


/**
 * Created by Airy on 2019-05-02
 * Mail: a532710813@gmail.com
 * Github: AiryMiku
 */

class ModifyPasswordViewModel: ViewModel() {

    val result: MutableLiveData<Boolean> = MutableLiveData()
    val repository = UserRepository.getInstance()


    fun changePassword(params: Map<String, Any>) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val r = repository.changePassword(params)
                withContext(Dispatchers.Main) {
                    result.value = r.code == 0
                }
            }catch (e: Exception) {
                e.printStackTrace()
                withContext(Dispatchers.Main) {
                    result.value = false
                }
            }
        }
    }

}