package com.airy.juju.ui.activity

import com.airy.juju.R
import com.airy.juju.base.BaseActivity
import kotlinx.android.synthetic.main.activity_login.*
import android.app.ProgressDialog
import android.util.Log
import android.content.Intent
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.airy.juju.bean.User
import com.airy.juju.repository.UserRepository
import com.airy.juju.util.UserCenter
import com.airy.juju.viewModel.activity.LoginAndSignUpViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception


/**
 * Created by Airy on 2019/3/11
 * Mail: a532710813@gmail.com
 * Github: AiryMiku
 */

class LoginActivity : BaseActivity() {

    private lateinit var viewMode: LoginAndSignUpViewModel
    private lateinit var progressDialog: ProgressDialog

    companion object {
        const val REQUEST_SIGNUP = 0
    }

    override fun toSetContentView() {
        setContentView(R.layout.activity_login)
    }

    override fun initViews() {
        super.initViews()

        viewMode = ViewModelProviders.of(this).get(LoginAndSignUpViewModel::class.java)
        btn_login.setOnClickListener {
            login()
        }

        link_signup.setOnClickListener {
            val intent = Intent(applicationContext, SignUpActivity::class.java)
            startActivityForResult(intent, REQUEST_SIGNUP)
            finish()
            overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out)
        }

        subsrcibeUI()
    }

    private fun subsrcibeUI() {
        viewMode.token.observe(this , Observer {
            if (it.code == 0) {
                onLoginSuccess()
                UserCenter.saveLogin(this, it.data.user_id, it.data.access_token)
//                makeToast("user id->"+it.data.user_id+"token->"+it.data.access_token)
            } else {
                onLoginFailed()
            }
            progressDialog.dismiss()
        })
    }

    private fun login() {
        Log.d(TAG, "Login")

        if (!validate()) {
            onLoginFailed()
            return
        }

        btn_login.isEnabled = false

        progressDialog = ProgressDialog(
            this@LoginActivity,
            R.style.AppTheme_Dark_Dialog
        )
        progressDialog.isIndeterminate = true
        progressDialog.setMessage("登录中...")
        progressDialog.show()

        val accountName = login_input_account.text.toString()
        val password = login_input_password.text.toString()

        val params = HashMap<String, Any>()
        params["account_name"] = accountName
        params["password"] = password

        viewMode.login(params)
    }

    private fun validate(): Boolean {
        var valid = true

        val account = login_input_account.text.toString()
        val password = login_input_password.text.toString()

        if (account.isEmpty()) {
            login_input_account.error = "账号不为空"
            valid = false
        } else {
            login_input_account.error = null
        }

        if (password.isEmpty()) {
            login_input_password.error = "密码不为空！"
            valid = false
        } else {
            login_input_password.error = null
        }
        return valid
    }

    fun onLoginSuccess() {
        btn_login.isEnabled = true
        makeToast("登录成功")
        activityIntentTo(MainActivity::class.java)
        finish()
    }

    fun onLoginFailed() {
        makeToast("登录失败")
        btn_login.isEnabled = true
    }
}