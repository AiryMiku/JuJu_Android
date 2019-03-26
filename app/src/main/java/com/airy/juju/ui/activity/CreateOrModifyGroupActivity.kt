package com.airy.juju.ui.activity

import android.view.MenuItem
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.airy.juju.R
import com.airy.juju.base.BaseActivity
import com.airy.juju.databinding.ActivityCreateOrModifyGroupBinding
import com.airy.juju.viewModel.activity.CreateOrModifyGroupViewModel
import com.airy.juju.viewModel.factroy.CreateOrModifyGroupViewModelFactory
import kotlinx.android.synthetic.main.layout_app_bar.*

class CreateOrModifyGroupActivity : BaseActivity() {

    companion object {
        val TYPE_KEY: String = "TYPE_KEY"
        val CREATE_KEY: String = "CREATE_KEY"
        val MODIFY_KEY: String = "MODIFY_KEY"
    }

    private lateinit var binding: ActivityCreateOrModifyGroupBinding
    private lateinit var viewModel: CreateOrModifyGroupViewModel

    private var id: Int = 0

    override fun toSetContentView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_create_or_modify_group)
    }

    override fun initViews() {
        super.initViews()
        setSupportActionBar(toolbar)
        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true)
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setDisplayShowTitleEnabled(false)
        }

        binding.create.setOnClickListener {

        }

        binding.modify.setOnClickListener {
            val params = HashMap<String,Any>()
            params["group_id"] = id
            params["name"] = binding.inputGroupName.text
            params["introduction"] = binding.inputIntroduction.text
            viewModel.modifyGroup(params)
            finish()
        }
        typeControl()

        id = intent.getIntExtra(GroupDetailActivity.GROUP_ID_KEY,0)
        viewModel = ViewModelProviders.of(this, CreateOrModifyGroupViewModelFactory(id)).get(CreateOrModifyGroupViewModel::class.java)

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
        val type = itn.getStringExtra(TYPE_KEY)
        when(type) {
            CREATE_KEY -> {
                binding.linearLayout.removeView(binding.modify)
            }
            MODIFY_KEY -> {
                binding.linearLayout.removeView(binding.create)
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

        viewModel.result.observe(this, Observer {
            if (it.code == 0) {
                makeToast("Success")
            } else {
                makeToast("Failed")
            }
        })
    }

}
