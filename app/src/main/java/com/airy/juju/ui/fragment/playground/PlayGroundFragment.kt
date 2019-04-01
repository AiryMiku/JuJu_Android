package com.airy.juju.ui.fragment.playground

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.airy.juju.Common
import com.airy.juju.base.BaseFragment
import com.airy.juju.databinding.FragmentPlaygroundBinding
import com.airy.juju.ui.activity.ActivityDetailActivity
import com.airy.juju.ui.adapter.listView.ActivitiesAdapter


/**
 * Created by Airy on 2019/3/31
 * Mail: a532710813@gmail.com
 * Github: AiryMiku
 */

class PlayGroundFragment: BaseFragment() {

    private lateinit var binding: FragmentPlaygroundBinding
    private lateinit var viewModel: PlayGroundViewModel
    private lateinit var adapter: ActivitiesAdapter

    companion object {
        fun newInstance() = PlayGroundFragment()
    }

    override fun initPrepare() {
        viewModel = ViewModelProviders.of(this).get(PlayGroundViewModel::class.java)
        initRefresh()
        initRecycleView()
        subscribeUI()
    }

    override fun onInvisible() {}

    override fun initData() {}

    override fun initView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentPlaygroundBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun initRecycleView() {
        adapter = ActivitiesAdapter {
            makeToast("GroupID -> "+it.id)
            val intent = Intent(context, ActivityDetailActivity::class.java)
            intent.putExtra(Common.ParamTranferKey.ACTIVITY_ID_KEY, it.id)
            startActivity(intent)
        }
        binding.list.adapter = adapter
    }


    private fun initRefresh() {
        binding.refresh.setOnRefreshListener {
            binding.refresh.isRefreshing = true
            viewModel.refresh()
        }
    }

    private fun subscribeUI() {
        viewModel.activities.observe(this, Observer {
            adapter.submitList(it.data.list)
            if (it.code == 0) {
                makeToast("成功拉取，总共有"+it.data.count+"个数据")
                binding.refresh.isRefreshing = false
            } else {
                makeToast("拉取失败")
            }
        })
    }
}