package com.airy.juju.ui.fragment.me

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.airy.juju.Common
import com.airy.juju.base.BaseFragment
import com.airy.juju.databinding.FragmentMeBinding
import com.airy.juju.ui.activity.CreateOrModifyGroupActivity
import com.airy.juju.ui.activity.ItemListActivity
import com.airy.juju.ui.activity.ModifyMyInfoActivity
import com.airy.juju.util.UserCenter


/**
 * Created by Airy on 2019/3/15
 * Mail: a532710813@gmail.com
 * Github: AiryMiku
 */

class MeFragment: BaseFragment() {

    private lateinit var binding: FragmentMeBinding
    private lateinit var viewModel: MeViewModel

    companion object {
        fun newInstance() = MeFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProviders.of(this).get(MeViewModel::class.java)
    }

    override fun initView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentMeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun initPrepare() {
        binding.myGroup.setOnClickListener {
            val intent = Intent(activity, ItemListActivity::class.java)
            intent.putExtra(Common.ItemListTypeKey.TYPE_KEY, Common.ItemListTypeKey.MY_GROUP)
            startActivity(intent)
        }
        binding.myActivity.setOnClickListener {
            val intent = Intent(activity, ItemListActivity::class.java)
            intent.putExtra(Common.ItemListTypeKey.TYPE_KEY, Common.ItemListTypeKey.MY_ACTIVITY)
            startActivity(intent)
        }
        binding.myFollowUsers.setOnClickListener {
            val intent = Intent(activity, ItemListActivity::class.java)
            intent.putExtra(Common.ItemListTypeKey.TYPE_KEY, Common.ItemListTypeKey.MY_FOLLOW_USER)
            startActivity(intent)
        }
        binding.createGroup.setOnClickListener {
            val intent = Intent(activity, CreateOrModifyGroupActivity::class.java)
            intent.putExtra(Common.ActivityCreateOrModifyKey.TYPE_KEY, Common.ActivityCreateOrModifyKey.CREATE_KEY)
            startActivity(intent)
        }
        binding.modifyInfo.setOnClickListener {
            val intent = Intent(activity, ModifyMyInfoActivity::class.java)
            startActivity(intent)
        }
        binding.modifyPassword.setOnClickListener {
            //todo
        }
        binding.logout.setOnClickListener {
            UserCenter.logout()
            makeToast("登出系统")
            activity?.finish()
        }
        subsrcibeUI()
    }

    private fun subsrcibeUI() {
        viewModel.user.observe(this, Observer {
            if (it.code == 0) {
                binding.userName.text = it.data.nickname
            }
        })
    }

    override fun onInvisible() {}

    override fun initData() {}

}