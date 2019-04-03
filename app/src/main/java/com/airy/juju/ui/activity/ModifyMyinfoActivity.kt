package com.airy.juju.ui.activity

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.DialogInterface
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.SwitchCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.airy.juju.R
import com.airy.juju.base.BaseActivity
import com.airy.juju.databinding.ActivityModifyMyinfoBinding
import com.airy.juju.util.UserCenter
import com.airy.juju.viewModel.activity.ModifyMyInfoViewModel
import java.util.*
import kotlin.collections.HashMap

class ModifyMyinfoActivity : BaseActivity() {

    private lateinit var binding: ActivityModifyMyinfoBinding
    private lateinit var viewModel: ModifyMyInfoViewModel

    override fun toSetContentView() {
       binding = DataBindingUtil.setContentView(this, R.layout.activity_modify_myinfo)
    }

    override fun loadData() {
        super.loadData()
        viewModel = ViewModelProviders.of(this).get(ModifyMyInfoViewModel::class.java)
        viewModel.fetchUserInfo()
    }

    override fun initViews() {
        super.initViews()
        initToolBar(true, true, true)
        setToolBarTitle("修改个人资料")
        binding.nickname.setOnClickListener {
            showEditDialog("编辑昵称", binding.nickname.text.toString()) {
                val params = HashMap<String, Any>()
                params["access_token"] = UserCenter.getUserToken()
                params["nickname"] = it
                viewModel.modifyUserInfo(params)
            }
        }
        binding.sex.setOnClickListener {
            showSexChoiceDialog("选择性别")
        }
        binding.birth.setOnClickListener {
            showDateTimeByPicker{
                val params = HashMap<String, Any>()
                params["access_token"] = UserCenter.getUserToken()
                params["birth"] = it
                viewModel.modifyUserInfo(params)
            }
        }
        binding.phone.setOnClickListener {
            showEditDialog("编辑电话", binding.phone.text.toString()) {
                if (it.length <= 13) {
                    val params = HashMap<String, Any>()
                    params["access_token"] = UserCenter.getUserToken()
                    params["phone"] = it
                    viewModel.modifyUserInfo(params)
                } else {
                    makeToast("电话长度超过13")
                }
            }
        }
        binding.status.setOnClickListener {
            showEditDialog("编辑签名", binding.status.text.toString()) {
                val params = HashMap<String, Any>()
                params["access_token"] = UserCenter.getUserToken()
                params["status"] = it
                viewModel.modifyUserInfo(params)
            }
        }

        binding.personalInfoPrivacy.setOnClickListener {
            showPrivacySettingDialog()
        }

        binding.switchEnableSearch.setOnCheckedChangeListener {
                buttonView, isChecked ->
            buttonView.isChecked = isChecked
        }

        binding.switchFool.setOnCheckedChangeListener {
                buttonView, isChecked ->
            if (isChecked) {
                buttonView.isChecked = true
                makeToast("正在启动自爆程序，电池过热，做好准备")
                makeToast("骗你的~")
            } else {
                buttonView.isChecked = false
                makeToast("客官不再玩一次？")
            }
        }
        subsrcibeUI()
    }

    private fun subsrcibeUI() {
        viewModel.user.observe(this, Observer {
            binding.user = it.data
            when(it.data.sex) {
                -1 -> binding.sex.text = "保密"
                0 -> binding.sex.text = "女"
                1 -> binding.sex.text = "男"
            }
        })

        viewModel.modifyResult.observe(this, Observer {
            if (it) {
                makeSnackar(binding.linearLayout, "修改成功")
            } else {
                makeSnackar(binding.linearLayout, "修改失败")
            }
            viewModel.fetchUserInfo()
        })
    }

    private fun showEditDialog(title: String, content: String, confirmCallBack: (String)->Unit) {
        val dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder.setTitle(title)
        val editText = EditText(this)
        editText.setText(content)
        dialogBuilder.setView(editText)
        dialogBuilder
            .setPositiveButton("确认") { _, _ -> // dialog, which
                confirmCallBack(editText.text.toString())
            }
        dialogBuilder
            .setNegativeButton("取消") { _, _ ->
                makeSnackar(binding.linearLayout, "取消")
            }
        dialogBuilder.show()
    }

    private fun showDateTimeByPicker(confirmCallBack: (String) -> Unit) {
        val c = Calendar.getInstance()
        val mYear = c.get(Calendar.YEAR)
        val mMonth = c.get(Calendar.MONTH)
        val mDay = c.get(Calendar.DAY_OF_MONTH)
        val dateDialog = DatePickerDialog(this, DatePickerDialog.OnDateSetListener {
                _, year, month, dayOfMonth ->
            val dateString = "$year-"+(month+1)+"-$dayOfMonth"
            confirmCallBack(dateString)
        }, mYear, mMonth, mDay)
        dateDialog.show()
    }

    private fun showSexChoiceDialog(title: String) {
        var sex: Int = -1
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_sex_choice, null) as LinearLayout
        val radioGroup = dialogView.findViewById<RadioGroup>(R.id.radio_group)
        val btnSecret = radioGroup.findViewById<RadioButton>(R.id.radio_btn_secret)
        val btnFemale = radioGroup.findViewById<RadioButton>(R.id.radio_btn_female)
        val btnMale = radioGroup.findViewById<RadioButton>(R.id.radio_btn_male)
        when(binding.user?.sex) {
            -1 -> btnSecret.isChecked = true
            0 -> btnFemale.isChecked = true
            1 -> btnMale.isChecked = true
        }
        radioGroup.setOnCheckedChangeListener {
                group, checkedId ->
            when(checkedId) {
                btnSecret.id -> sex = -1
                btnFemale.id -> sex = 0
                btnMale.id -> sex = 1
            }
        }
        val dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder.setTitle(title)
        dialogBuilder.setView(dialogView)
        dialogBuilder
            .setPositiveButton("确认") { _, _ -> // dialog, which
                val params = HashMap<String, Any>()
                params["access_token"] = UserCenter.getUserToken()
                params["sex"] = sex
                viewModel.modifyUserInfo(params)
            }
        dialogBuilder
            .setNegativeButton("取消") { _, _ ->
                makeSnackar(binding.linearLayout, "取消")
            }
        dialogBuilder.show()
    }

    private fun showPrivacySettingDialog() {
//        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_privacy, null) as LinearLayout
//        val cbBitrh = dialogView.findViewById<SwitchCompat>(R.id.birth)
//        val cbPhone = dialogView.findViewById<SwitchCompat>(R.id.phone)
//        val cbStatus = dialogView.findViewById<SwitchCompat>(R.id.status)
        val choices: Array<String> = arrayOf("生日","电话","签名")
        val bools = BooleanArray(3)
        bools[0] = false
        bools[1] = false
        bools[2] = false
        val dialog = AlertDialog.Builder(this)
        dialog.setTitle("个人资料隐私设置")
        dialog.setMultiChoiceItems(choices, bools) { dialog, which, isChecked ->
            bools[which] = isChecked
        }
        dialog
            .setPositiveButton("确认") { _, _ -> // dialog, which

            }
        dialog
            .setNegativeButton("取消") { _, _ ->
                makeSnackar(binding.linearLayout, "取消")
            }
        dialog.show()
    }
}
