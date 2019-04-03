package com.airy.juju.viewModel.activity

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.airy.juju.api.ReturnResult
import com.airy.juju.bean.Token
import com.airy.juju.repository.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception


/**
 * Created by Airy on 2019/3/30
 * Mail: a532710813@gmail.com
 * Github: AiryMiku
 */

class LoginAndSignUpViewModel: ViewModel() {

    val repository = UserRepository.getInstance()
    val token: MutableLiveData<ReturnResult<Token>> = MutableLiveData()
    val signUpResult: MutableLiveData<Boolean> = MutableLiveData()

    fun login(params: HashMap<String, Any>) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val r = repository.login(params)
                withContext(Dispatchers.Main) {
                    token.value = r
                }
            }catch (e : Exception) {
                e.printStackTrace()
            }
        }
    }

    fun signUp(params: HashMap<String, Any>) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val r = repository.signUp(params)
                withContext(Dispatchers.Main) {
                    signUpResult.value = r.code == 0
                }
            }catch (e : Exception) {
                e.printStackTrace()
                withContext(Dispatchers.Main) {
                    signUpResult.value = false
                }
            }
        }
    }
}