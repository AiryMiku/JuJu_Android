package com.airy.juju.viewModel.activity

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.airy.juju.bean.ListData
import com.airy.juju.bean.Message


/**
 * Created by Airy on 2019/4/2
 * Mail: a532710813@gmail.com
 * Github: AiryMiku
 */

class ChatViewModel: ViewModel() {

    val messages: MutableLiveData<ListData<Message>> = MutableLiveData()

}