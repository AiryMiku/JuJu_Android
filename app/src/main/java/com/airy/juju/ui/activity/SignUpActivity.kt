package com.airy.juju.ui.activity

import android.app.Activity
import android.app.ProgressDialog
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.airy.juju.R
import com.airy.juju.base.BaseActivity
import com.airy.juju.repository.UserRepository
import com.airy.juju.util.UserCenter
import com.airy.juju.viewModel.activity.LoginAndSignUpViewModel
import kotlinx.android.synthetic.main.activity_sign_up.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception


class SignUpActivity : BaseActivity() {

    private lateinit var viewMode: LoginAndSignUpViewModel
    private lateinit var progressDialog: ProgressDialog

    override fun toSetContentView() {
        setContentView(R.layout.activity_sign_up)
    }

    override fun initViews() {
        super.initViews()


        viewMode = ViewModelProviders.of(this).get(LoginAndSignUpViewModel::class.java)
        btn_signup.setOnClickListener {
            signup()
        }

        link_login.setOnClickListener {
            activityIntentTo(LoginActivity::class.java)
            finish()
            overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out)
        }

        subsrcibeUI()
    }

    private fun subsrcibeUI() {
        viewMode.signUpResult.observe(this , Observer {
            if (it) {
                onSignupSuccess()
            } else {
                onSignupFailed()
            }
            progressDialog.dismiss()
        })
    }

    private fun signup() {
        Log.d(TAG, "Signup")

        if (!validate()) {
            onSignupFailed()
            return
        }

        btn_signup.isEnabled = false

        progressDialog = ProgressDialog(
            this@SignUpActivity,
            R.style.AppTheme_Dark_Dialog
        )
        progressDialog.isIndeterminate = true
        progressDialog.setMessage("正在注册...")
        progressDialog.show()

        val nickname = input_nickname.text.toString()
        val account = sign_up_input_account.text.toString()
        val password = sign_up_input_password.text.toString()

        val params = HashMap<String, Any>()
        params["account_name"] = account
        params["nickname"] = nickname
        params["password"] = password

        viewMode.signUp(params)
    }


    private fun onSignupSuccess() {
        btn_signup.isEnabled = true
        makeToast("注册成功")
        setResult(Activity.RESULT_OK, null)
        finish()
    }

    private fun onSignupFailed() {
        makeToast("注册失败")
        btn_signup.isEnabled = true
    }

    private fun validate(): Boolean {
        var valid = true

        val nickname = input_nickname.text.toString()
        val account = sign_up_input_account.text.toString()
        val password = sign_up_input_password.text.toString()
        val reEnterPassword = sign_up_input_reEnterPassword.text.toString()

        if (nickname.isEmpty() || nickname.length < 3) {
            input_nickname.error = "至少三个字符"
            valid = false
        } else {
            input_nickname.error = null
        }

        if (account.isEmpty()) {
            sign_up_input_account.error = "账号不能为空"
            valid = false
        } else {
            sign_up_input_account.error = null
        }

        if (password.length < 4) {
            sign_up_input_password.error = "至少4个字符"
            valid = false
        } else {
            sign_up_input_password.error = null
        }

        if (reEnterPassword != password) {
            sign_up_input_reEnterPassword.error = "密码不匹配"
            valid = false
        } else {
            sign_up_input_reEnterPassword.error = null
        }

        return valid
    }
}
