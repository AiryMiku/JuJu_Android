package com.airy.juju.ui.activity

import android.view.MenuItem
import android.view.View
import androidx.databinding.DataBindingUtil
import com.airy.juju.Common
import com.airy.juju.R
import com.airy.juju.base.BaseActivity
import com.airy.juju.databinding.ActivityCreateOrModifyActivityBinding
import kotlinx.android.synthetic.main.layout_app_bar.*

class CreateOrModifyActivityActivity : BaseActivity() {

//    private var isModify: Boolean = false
    private lateinit var binding: ActivityCreateOrModifyActivityBinding
//    private lateinit var viewModel

    override fun toSetContentView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_create_or_modify_activity)
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

        typeControl()

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
            }
            Common.ActivityCreateOrModifyKey.MODIFY_KEY -> {
                binding.linearLayout.removeView(binding.create)
            }
        }
    }

    private fun subscribeUI() {

    }
}
