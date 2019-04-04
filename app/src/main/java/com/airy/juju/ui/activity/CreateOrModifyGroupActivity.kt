package com.airy.juju.ui.activity

import android.content.Intent
import android.view.MenuItem
import androidx.appcompat.app.ActionBar
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.airy.juju.Common
import com.airy.juju.R
import com.airy.juju.base.BaseActivity
import com.airy.juju.databinding.ActivityCreateOrModifyGroupBinding
import com.airy.juju.util.UserCenter
import com.airy.juju.viewModel.activity.CreateOrModifyGroupViewModel
import com.airy.juju.viewModel.factroy.CreateOrModifyGroupViewModelFactory
import kotlinx.android.synthetic.main.layout_app_bar.*

class CreateOrModifyGroupActivity : BaseActivity() {

    private lateinit var binding: ActivityCreateOrModifyGroupBinding
    private lateinit var viewModel: CreateOrModifyGroupViewModel
    private lateinit var appBar: ActionBar

    private var id: Int = 0

    override fun toSetContentView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_create_or_modify_group)
    }

    override fun initViews() {
        super.initViews()
        setSupportActionBar(toolbar)
        appBar = this.supportActionBar!!
        appBar.setHomeButtonEnabled(true)
        appBar.setDisplayHomeAsUpEnabled(true)
        appBar.setDisplayShowTitleEnabled(true)

        id = intent.getIntExtra(Common.ParamTranferKey.GROUP_ID_KEY,0)
        viewModel = ViewModelProviders.of(this, CreateOrModifyGroupViewModelFactory(id)).get(CreateOrModifyGroupViewModel::class.java)

        binding.modify.setOnClickListener {
            if (validate()) {
                val params = HashMap<String,Any>()
                params["group_id"] = id
                params["name"] = binding.inputGroupName.text
                params["introduction"] = binding.inputIntroduction.text
                viewModel.modifyGroup(params)
            }
        }
        binding.create.setOnClickListener {
            if (validate()) {
                val params = HashMap<String,Any>()
                params["name"] = binding.inputGroupName.text
                params["introduction"] = binding.inputIntroduction.text
                params["access_token"] = UserCenter.getUserToken()
                viewModel.createGroup(params)
            }
        }

        typeControl()
        subscribeUI()
    }

    private fun validate(): Boolean {
        val name = binding.inputGroupName.text
        val intro = binding.inputIntroduction.text
        if (name.isEmpty() && intro.isEmpty()) {
            binding.inputGroupName.error = "必填，不为空"
            binding.inputIntroduction.error = "必填，不为空"
            return false
        }
        if (name.isEmpty()) {
            binding.inputGroupName.error = "必填，不为空"
            return false
        }
        if (intro.isEmpty()) {
            binding.inputIntroduction.error = "必填，不为空"
            return false
        }
        return true
    }

    private fun typeControl(){
        val itn = intent
        val type = itn.getStringExtra(Common.ActivityCreateOrModifyKey.TYPE_KEY)
        when(type) {
            Common.ActivityCreateOrModifyKey.CREATE_KEY -> {
                binding.linearLayout.removeView(binding.modify)
                appBar.title = "创建群组"
            }
            Common.ActivityCreateOrModifyKey.MODIFY_KEY -> {
                appBar.title = "修改群组"
                binding.linearLayout.removeView(binding.create)
                viewModel.refresh()
            }
        }
    }

    private fun subscribeUI() {
        viewModel.group.observe(this, Observer {
            if (it != null) {
                binding.group = it.data
            } else {
                makeToast("no data")
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

        viewModel.createResult.observe(this, Observer {
            if (it.code == 0) {
                makeToast("Success")
                val intent = Intent(this, GroupDetailActivity::class.java)
                intent.putExtra(Common.ParamTranferKey.GROUP_ID_KEY, it.data.id)
                startActivity(intent)
                finish()
            } else {
                makeToast("Failed")
            }
        })

    }

}
