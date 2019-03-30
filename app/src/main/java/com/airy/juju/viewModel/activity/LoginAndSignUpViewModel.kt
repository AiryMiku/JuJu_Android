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
 * Created by Airy on 2019/3/30
 * Mail: a532710813@gmail.com
 * Github: AiryMiku
 */

class LoginAndSignUpViewModel: ViewModel() {

    val repository = UserRepository.getInstance()
    val loginResult: MutableLiveData<Boolean> = MutableLiveData()
    val signUpResult: MutableLiveData<Boolean> = MutableLiveData()

    fun login(params: HashMap<String, Any>) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val r = repository.login(params)
                withContext(Dispatchers.Main) {
                    loginResult.value = r.code == 0
                }
            }catch (e : Exception) {
                e.printStackTrace()
                loginResult.value = false
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
                signUpResult.value = false
            }
        }
    }
}