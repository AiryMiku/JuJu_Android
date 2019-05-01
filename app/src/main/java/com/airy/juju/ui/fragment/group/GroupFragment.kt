package com.airy.juju.ui.fragment.group

import android.content.Intent
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.airy.juju.Common

import com.airy.juju.base.BaseFragment
import com.airy.juju.databinding.FragmentGroupBinding
import com.airy.juju.ui.activity.GroupDetailActivity
import com.airy.juju.ui.adapter.listView.GroupsAdapter


class GroupFragment : BaseFragment() {

    private lateinit var viewModel: GroupViewModel
    private lateinit var binding: FragmentGroupBinding
    private lateinit var adapter: GroupsAdapter

    companion object {
        fun newInstance() = GroupFragment()
    }

    override fun initView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentGroupBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun initPrepare() {
        viewModel = ViewModelProviders.of(this).get(GroupViewModel::class.java)
        initRecycleView()
        initRefresh()
        subscribeUI()
    }

    override fun onInvisible() {}

    override fun initData() {}

    private fun initRecycleView() {
        adapter = GroupsAdapter {
            makeToast("GroupID -> "+it.id)
            val intent = Intent(context, GroupDetailActivity::class.java)
            intent.putExtra(Common.ParamTranferKey.GROUP_ID_KEY, it.id)
            startActivity(intent)
        }
        binding.list.addOnScrollListener(object :RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {

            }

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                    val position = layoutManager.findLastVisibleItemPosition()

                    if (position == adapter.itemCount - 1) {
                        viewModel.fetchMoreGroups()
                    }
                }
            }
        })
        binding.list.adapter = adapter
    }

    private fun initRefresh() {
        binding.refresh.setOnRefreshListener {
            binding.refresh.isRefreshing = true
            viewModel.refresh()
        }
    }

    private fun subscribeUI() {
        viewModel.groups.observe(this, Observer {
            if (it.code == 0) {
                adapter.submitList(it.data.list)
                binding.refresh.isRefreshing = false
            } else {
                makeToast("拉取失败")
            }
        })
    }

}
