package com.airy.juju.ui.activity

import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.airy.juju.Common
import com.airy.juju.R
import com.airy.juju.base.BaseActivity
import com.airy.juju.databinding.ActivityUserDetailBinding
import com.airy.juju.viewModel.activity.GroupDetailViewModel
import com.airy.juju.viewModel.activity.UserDetailViewModel

class UserDetailActivity : BaseActivity() {

    private lateinit var viewModel: UserDetailViewModel
    private lateinit var binding: ActivityUserDetailBinding
    private var id: Int = 0

    override fun toSetContentView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_user_detail)
    }

    override fun loadData() {
        super.loadData()
        id = intent.getIntExtra(Common.ParamTranferKey.USER_ID_KEY, 0)
        viewModel = ViewModelProviders.of(this).get(UserDetailViewModel::class.java)
        viewModel.fetchUserInfo(id)
    }

    override fun initViews() {
        super.initViews()
        initToolBar(true, true, true)
        binding.refresh.setOnRefreshListener {
            binding.refresh.isRefreshing = true
            viewModel.fetchUserInfo(id)
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
            binding.refresh.isRefreshing = false
        })
    }
}
