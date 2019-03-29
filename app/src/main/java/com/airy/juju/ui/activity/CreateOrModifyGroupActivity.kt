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

        binding.create.setOnClickListener {
            val params = HashMap<String,Any>()
            params["name"] = binding.inputGroupName.text
            params["introduction"] = binding.inputIntroduction.text
            viewModel.createGroup(params)
        }
        id = intent.getIntExtra(Common.ParamTranferKey.GROUP_ID_KEY,0)
        viewModel = ViewModelProviders.of(this, CreateOrModifyGroupViewModelFactory(id)).get(CreateOrModifyGroupViewModel::class.java)

        binding.modify.setOnClickListener {
            val params = HashMap<String,Any>()
            params["group_id"] = id
            params["name"] = binding.inputGroupName.text
            params["introduction"] = binding.inputIntroduction.text
            viewModel.modifyGroup(params)
        }
        binding.create.setOnClickListener {
            val params = HashMap<String,Any>()
            params["group_id"] = id
            params["name"] = binding.inputGroupName.text
            params["introduction"] = binding.inputIntroduction.text
            viewModel.createGroup(params)
        }

        typeControl()
        subscribeUI()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
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
