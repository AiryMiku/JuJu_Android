package com.airy.juju.ui.activity

import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.airy.juju.R
import com.airy.juju.base.BaseActivity
import com.airy.juju.databinding.ActivityModifyPasswordBinding
import com.airy.juju.util.UserCenter
import com.airy.juju.viewModel.activity.ModifyPasswordViewModel

class ModifyPasswordActivity : BaseActivity() {

    private lateinit var binding: ActivityModifyPasswordBinding
    private lateinit var viewModel: ModifyPasswordViewModel

    override fun toSetContentView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_modify_password)
    }

    override fun initViews() {
        super.initViews()
        viewModel = ViewModelProviders.of(this).get(ModifyPasswordViewModel::class.java)
        binding.btnModfy.setOnClickListener {
            if (validate()) {
                val oldPw = binding.oldPassword.text.toString()
                val newPw = binding.newPassword.text.toString()
                val reEnterPw = binding.reEnterNewPassword.text.toString()
                val params = HashMap<String, Any>()
                params["access_token"] = UserCenter.getUserToken()
                params["ex_password"] = oldPw
                params["new_password"] = newPw
                params["confirm_password"] = reEnterPw
                viewModel.changePassword(params)
            }
        }
        subsrcibeUI()
    }

    private fun validate(): Boolean {
        var valid = true
        val oldPw = binding.oldPassword.text.toString()
        val newPw = binding.newPassword.text.toString()
        val reEnterPw = binding.reEnterNewPassword.text.toString()

        if (oldPw.length < 4) {
            binding.oldPassword.error = "至少4个字符"
            valid = false
        } else {
            binding.oldPassword.error = null
        }

        if (newPw.length < 4) {
            binding.newPassword.error = "至少4个字符"
            valid = false
        } else {
            binding.newPassword.error = null
        }

        if (reEnterPw != newPw) {
            binding.reEnterNewPassword.error = "密码不匹配"
            binding.newPassword.error = "密码不匹配"
            valid = false
        } else {
            binding.reEnterNewPassword.error = null
        }
        return valid
    }

    private fun subsrcibeUI() {
        viewModel.result.observe(this, Observer {
            if (it) {
                makeToast("修改成功,请重新登录")
                UserCenter.logout()
                activityIntentTo(LoginActivity::class.java)
                finish()
            } else {
                makeToast("修改失败")
            }
        })
    }
}
