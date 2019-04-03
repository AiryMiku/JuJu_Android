package com.airy.juju.ui.activity

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.airy.juju.Common
import com.airy.juju.R
import com.airy.juju.base.BaseActivity
import com.airy.juju.databinding.ActivityCreateOrModifyActivityBinding
import com.airy.juju.viewModel.activity.CreateOrModifyActivityViewModel
import com.airy.juju.viewModel.factroy.CreateOrModifyActivityViewModelFactory
import kotlinx.android.synthetic.main.layout_app_bar.*
import java.util.*


class CreateOrModifyActivityActivity : BaseActivity() {

    private lateinit var binding: ActivityCreateOrModifyActivityBinding
    private lateinit var viewModel: CreateOrModifyActivityViewModel
    private lateinit var appBar: ActionBar

    private var id: Int = 0
    private var groupId: Int = 0

    override fun toSetContentView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_create_or_modify_activity)
    }

    override fun initViews() {
        super.initViews()
        setSupportActionBar(toolbar)
        appBar = this.supportActionBar!!
        appBar.setHomeButtonEnabled(true)
        appBar.setDisplayHomeAsUpEnabled(true)
        appBar.setDisplayShowTitleEnabled(false)
        id = intent.getIntExtra(Common.ParamTranferKey.ACTIVITY_ID_KEY,0)
        groupId = intent.getIntExtra(Common.ParamTranferKey.GROUP_ID_KEY, 0)
        viewModel = ViewModelProviders.of(this, CreateOrModifyActivityViewModelFactory(id)).get(CreateOrModifyActivityViewModel::class.java)
        typeControl()

        binding.modify.setOnClickListener {
            val params = HashMap<String,Any>()
            params["activity_id"] = id
            params["title"] = binding.inputActTitle.text
            params["content"] = binding.inputContent.text
            params["place"] = binding.inputPlace.text
            params["start_time"] = binding.startTime.text
            params["end_time"] = binding.endTime.text
            viewModel.modifyActivity(params)
        }
        binding.create.setOnClickListener {
            val params = HashMap<String,Any>()
            params["group_id"] = groupId
            params["title"] = binding.inputActTitle.text
            params["content"] = binding.inputContent.text
            params["place"] = binding.inputPlace.text
            params["start_time"] = binding.startTime.text
            params["end_time"] = binding.endTime.text
            viewModel.createActivity(params)
        }

        binding.startTime.setOnClickListener {
            setDateTimeByPicker(it as TextView)
        }
        binding.endTime.setOnClickListener {
            setDateTimeByPicker(it as TextView)
        }
        typeControl()
        subscribeUI()
    }

    private fun typeControl(){
        val itn = intent
        val type = itn.getStringExtra(Common.ActivityCreateOrModifyKey.TYPE_KEY)
        when(type) {
            Common.ActivityCreateOrModifyKey.CREATE_KEY -> {
                binding.linearLayout.removeView(binding.modify)
            }
            Common.ActivityCreateOrModifyKey.MODIFY_KEY -> {
                binding.linearLayout.removeView(binding.create)
                viewModel.refresh()
            }
        }
    }

    private fun subscribeUI() {
        viewModel.activity.observe(this, Observer {
            if (it.code == 0) {
                binding.activity = it.data
            }
        })

        viewModel.createResult.observe(this, Observer {
            if (it.code == 0) {
                makeToast("Success")
                finish()
            } else {
                makeToast("Failed")
            }
        })

        viewModel.modifyResult.observe(this, Observer {
            if (it.code == 0) {
                makeToast("Success")
                finish()
            } else {
                makeToast("Failed")
            }
        })
    }

    private fun setDateTimeByPicker(tv: TextView) {
        val c = Calendar.getInstance()
        val mYear = c.get(Calendar.YEAR)
        val mMonth = c.get(Calendar.MONTH)
        val mDay = c.get(Calendar.DAY_OF_MONTH)
        val hour = c.get(Calendar.HOUR)
        val minute = c.get(Calendar.MINUTE)

        var dateString: String = ""
        var timeString: String = ""
        val timeDialog = TimePickerDialog(this, TimePickerDialog.OnTimeSetListener {
                _, hourOfDay, minute ->
            timeString = "$hourOfDay:$minute:00"
            tv.text = dateString + " " + timeString
        }, hour, minute, true)

        val dateDialog = DatePickerDialog(this, DatePickerDialog.OnDateSetListener {
                _, year, month, dayOfMonth ->
            dateString = "$year-"+(month+1)+"-$dayOfMonth"
            timeDialog.show()
        }, mYear, mMonth, mDay)
        dateDialog.show()
    }
}
