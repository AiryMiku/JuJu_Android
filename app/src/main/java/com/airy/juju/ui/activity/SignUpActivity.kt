package com.airy.juju.ui.activity

import android.app.Activity
import android.app.ProgressDialog
import android.util.Log
import com.airy.juju.R
import com.airy.juju.base.BaseActivity
import kotlinx.android.synthetic.main.activity_sign_up.*


class SignUpActivity : BaseActivity() {

    override fun initViews() {
        super.initViews()
    }

    override fun loadData() {
        super.loadData()
    }

    override fun setContentViewId(): Int {
        return R.layout.activity_sign_up
    }

    fun signup() {
        Log.d(TAG, "Signup")

        if (!validate()) {
            onSignupFailed()
            return
        }

        btn_signup.isEnabled = false

        val progressDialog = ProgressDialog(
            this@SignUpActivity,
            R.style.AppTheme_Dark_Dialog
        )
        progressDialog.isIndeterminate = true
        progressDialog.setMessage("正在注册...")
        progressDialog.show()

        val nickname = input_nickname.text.toString()
        val account = sign_up_input_account.text.toString()
        val password = sign_up_input_password.text.toString()
        val reEnterPassword = sign_up_input_reEnterPassword.text.toString()

        // TODO: Implement your own signup logic here.

        android.os.Handler().postDelayed(
            {
                // On complete call either onSignupSuccess or onSignupFailed
                // depending on success
                onSignupSuccess()
                // onSignupFailed();
                progressDialog.dismiss()
            }, 3000
        )
    }


    fun onSignupSuccess() {
        btn_signup.setEnabled(true)
        setResult(Activity.RESULT_OK, null)
        finish()
    }

    fun onSignupFailed() {
        makeToast("注册失败")

        btn_signup.isEnabled = true
    }

    fun validate(): Boolean {
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

        if (password.isEmpty() || password.length < 4) {
            sign_up_input_password.error = "至少4个字符"
            valid = false
        } else {
            sign_up_input_password.error = null
        }

        if (reEnterPassword.isEmpty() || reEnterPassword.length < 4 || reEnterPassword != password) {
            sign_up_input_reEnterPassword.error = "密码不匹配"
            valid = false
        } else {
            sign_up_input_reEnterPassword.error = null
        }

        return valid
    }
}
