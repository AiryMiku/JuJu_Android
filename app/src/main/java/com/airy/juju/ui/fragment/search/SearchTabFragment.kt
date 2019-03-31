package com.airy.juju.ui.fragment.search

import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.airy.juju.Common
import com.airy.juju.R
import com.airy.juju.base.BaseFragment
import com.airy.juju.databinding.FragmentSearchTabBinding
import com.airy.juju.eventBus.MessageEvent
import com.airy.juju.ui.adapter.listView.ActivitiesAdapter
import com.airy.juju.ui.adapter.listView.GroupsAdapter
import com.airy.juju.ui.adapter.listView.UsersAdapter
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode


/**
 * Created by Airy on 2019/3/25
 * Mail: a532710813@gmail.com
 * Github: AiryMiku
 */

class SearchTabFragment :BaseFragment() {

    private lateinit var viewModel: SearchTabViewModel
    private lateinit var binding: FragmentSearchTabBinding
    private lateinit var activitiesAdapter: ActivitiesAdapter
    private lateinit var groupsAdapter: GroupsAdapter
    private lateinit var usersAdapter: UsersAdapter
    private lateinit var type: String
    private lateinit var keyword: String

    companion object {

        fun newInstance(type: String): SearchTabFragment {
            val args = Bundle()
            args.putString(Common.SearchKey.TYPE_KEY, type)
            val fragment = SearchTabFragment()
            fragment.arguments = args
            return fragment
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onSearchEvent(messageEvent: MessageEvent) {
        keyword = messageEvent.message
        startSearch(keyword)
        binding.refresh.isRefreshing = true
    }

    override fun initPrepare() {
        viewModel = ViewModelProviders.of(this).get(SearchTabViewModel::class.java)
        initType()
        binding.refresh.setOnRefreshListener {
            startSearch(keyword)
        }
    }

    override fun onInvisible() {
        Log.d("Search", "onInvisible")
    }

    override fun initData() {}

    override fun initView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentSearchTabBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun initType() {
        when(type) {
            Common.SearchKey.ACTIVITY -> {
                initActivity()
            }
            Common.SearchKey.GROUP -> {
                initGroup()
            }
            Common.SearchKey.USER -> {
                initUser()
            }
        }
    }

    private fun initActivity() {
        activitiesAdapter = ActivitiesAdapter {

        }
        binding.list.adapter = activitiesAdapter
        viewModel.activities.observe(this, Observer {
            activitiesAdapter.submitList(it.data.list)
            binding.refresh.isRefreshing = false
        })
    }

    private fun initGroup() {
        groupsAdapter = GroupsAdapter {

        }
        binding.list.adapter = groupsAdapter
        viewModel.groups.observe(this, Observer {
            groupsAdapter.submitList(it.data.list)
            binding.refresh.isRefreshing = false
        })
    }

    private fun initUser() {
        usersAdapter = UsersAdapter {

        }
        binding.list.adapter = usersAdapter
        viewModel.users.observe(this, Observer {
            usersAdapter.submitList(it.data.list)
            binding.refresh.isRefreshing = false
        })
    }

    private fun startSearch(keyword: String) {
        when(type) {
            Common.SearchKey.ACTIVITY -> {
                viewModel.searchActivitys(keyword)
            }
            Common.SearchKey.GROUP -> {
                viewModel.searchGroups(keyword)
            }
            Common.SearchKey.USER -> {
                viewModel.searchUsers(keyword)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val args = arguments
        if (args != null) {
            type = args.getString(Common.SearchKey.TYPE_KEY)
        }
        EventBus.getDefault().register(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }
}