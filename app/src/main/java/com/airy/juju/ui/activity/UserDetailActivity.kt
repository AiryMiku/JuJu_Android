package com.airy.juju.ui.activity

import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.airy.juju.Common
import com.airy.juju.R
import com.airy.juju.base.BaseActivity
import com.airy.juju.databinding.ActivityUserDetailBinding
import com.airy.juju.util.UserCenter
import com.airy.juju.viewModel.activity.UserDetailViewModel
import com.airy.juju.viewModel.factroy.UserDetailViewModelFactory

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
        viewModel = ViewModelProviders.of(this, UserDetailViewModelFactory(id)).get(UserDetailViewModel::class.java)
    }

    override fun initViews() {
        super.initViews()
        initToolBar(true, true, true)
        binding.refresh.setOnRefreshListener {
            binding.refresh.isRefreshing = true
            viewModel.refresh()
        }
        if (id == UserCenter.getUserId()) {
            binding.btnFollow.visibility = View.GONE
        }
        // follow
        binding.btnFollow.setOnClickListener {
            when(binding.btnFollow.text) {
                "关注" -> {
                    val params = HashMap<String, Any>()
                    params["user_id"] = id
                    params["access_token"] = UserCenter.getUserToken()
                    viewModel.follow(params)
                }
                "已关注" -> {
                    val params = HashMap<String, Any>()
                    params["user_id"] = id
                    params["access_token"] = UserCenter.getUserToken()
                    viewModel.disfollow(params)
                }
            }
        }
        // message
        binding.btnMessage.setOnClickListener {

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
        viewModel.isFollowResult.observe(this, Observer {
            if (it.data.is_follow) {
                binding.btnFollow.text = "已关注"
            } else {
                binding.btnFollow.text = "关注"
            }
        })

        viewModel.followResult.observe(this, Observer {
            if (it) {
                binding.btnFollow.text = "已关注"
                makeToast("操作成功")
            } else {
                makeToast("操作失败")
            }
        })

        viewModel.disFollowResult.observe(this, Observer {
            if (it) {
                binding.btnFollow.text = "关注"
                makeToast("操作成功")
            } else {
                makeToast("操作失败")
            }
        })
    }
}
