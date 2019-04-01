package com.airy.juju.ui.activity

import android.app.DatePickerDialog
import android.content.DialogInterface
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.airy.juju.R
import com.airy.juju.base.BaseActivity
import com.airy.juju.databinding.ActivityModifyMyinfoBinding
import com.airy.juju.viewModel.activity.ModifyMyInfoViewModel
import java.util.*

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
                makeToast("Success")
            }
        }
        binding.sex.setOnClickListener {
            showSexChoiceDialog("选择性别")
        }
        binding.birth.setOnClickListener {
            showDateTimeByPicker()
        }
        binding.phone.setOnClickListener {
            showEditDialog("编辑电话", binding.phone.text.toString()) {
                makeToast("Success")
            }
        }
        binding.status.setOnClickListener {
            showEditDialog("编辑签名", binding.status.text.toString()) {
                makeToast("Success")
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
    }

    private fun showEditDialog(title: String, content: String, confirmCallBack: ()->Unit) {
        val dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder.setTitle(title)
        val editText = EditText(this)
        editText.setText(content)
        dialogBuilder.setView(editText)
        dialogBuilder
            .setPositiveButton("确认") { _, _ -> // dialog, which
                confirmCallBack()
            }
        dialogBuilder
            .setNegativeButton("取消") { _, _ ->
                makeSnackar(binding.linearLayout, "取消")
            }
        dialogBuilder.show()
    }

    private fun showDateTimeByPicker() {
        val c = Calendar.getInstance()
        val mYear = c.get(Calendar.YEAR)
        val mMonth = c.get(Calendar.MONTH)
        val mDay = c.get(Calendar.DAY_OF_MONTH)

        val dateDialog = DatePickerDialog(this, DatePickerDialog.OnDateSetListener {
                _, year, month, dayOfMonth ->
            val dateString = "$year-"+(month+1)+"-$dayOfMonth"
            // todo
        }, mYear, mMonth, mDay)
        dateDialog.show()
    }

    private fun showSexChoiceDialog(title: String) {
        var sex: Int
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_sex_choice, null) as LinearLayout
        val radioGroup = dialogView.findViewById<RadioGroup>(R.id.radio_group)
        val btnSecret = radioGroup.findViewById<RadioButton>(R.id.radio_btn_secret)
        val btnFemale = radioGroup.findViewById<RadioButton>(R.id.radio_btn_female)
        val btnMale = radioGroup.findViewById<RadioButton>(R.id.radio_btn_male)
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

            }
        dialogBuilder
            .setNegativeButton("取消") { _, _ ->
                makeSnackar(binding.linearLayout, "取消")
            }
        dialogBuilder.show()
    }
}
