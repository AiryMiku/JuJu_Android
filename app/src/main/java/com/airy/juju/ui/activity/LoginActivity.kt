package com.airy.juju.ui.activity

import com.airy.juju.R
import com.airy.juju.base.BaseActivity
import kotlinx.android.synthetic.main.activity_login.*
import android.app.ProgressDialog
import android.util.Log
import android.content.Intent




/**
 * Created by Airy on 2019/3/11
 * Mail: a532710813@gmail.com
 * Github: AiryMiku
 */

class LoginActivity : BaseActivity() {

    private val REQUEST_SIGNUP = 0

    override fun setContentViewId(): Int {
        return com.airy.juju.R.layout.activity_login
    }

    override fun initViews() {
        super.initViews()
        btn_login.setOnClickListener {
            login()
        }

        link_signup.setOnClickListener {
            val intent = Intent(applicationContext, SignUpActivity::class.java)
            startActivityForResult(intent, REQUEST_SIGNUP)
            finish()
            overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out)
        }
    }


    fun login() {
        Log.d(TAG, "Login")

        if (!validate()) {
            onLoginFailed()
            return
        }

        btn_login.isEnabled = false

        val progressDialog = ProgressDialog(
            this@LoginActivity,
            R.style.AppTheme_Dark_Dialog
        )
        progressDialog.isIndeterminate = true
        progressDialog.setMessage("登录中...")
        progressDialog.show()

        val account = login_input_account.text.toString()
        val password = login_input_password.text.toString()

        // TODO: Implement your own authentication logic here.

        android.os.Handler().postDelayed(
            {
                // On complete call either onLoginSuccess or onLoginFailed
                onLoginSuccess()
                // onLoginFailed();
                progressDialog.dismiss()
            }, 3000
        )
    }

    fun validate(): Boolean {
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
        finish()
    }

    fun onLoginFailed() {
        makeToast("登录失败")

        btn_login.isEnabled = true
    }
}