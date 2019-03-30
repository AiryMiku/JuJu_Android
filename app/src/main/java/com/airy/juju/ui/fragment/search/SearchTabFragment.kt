package com.airy.juju.ui.fragment.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.airy.juju.Common
import com.airy.juju.base.BaseFragment
import com.airy.juju.databinding.FragmentTabBinding
import com.airy.juju.ui.adapter.listView.ActivitiesAdapter
import com.airy.juju.ui.adapter.listView.GroupsAdapter
import com.airy.juju.ui.adapter.listView.UsersAdapter


/**
 * Created by Airy on 2019/3/25
 * Mail: a532710813@gmail.com
 * Github: AiryMiku
 */

class SearchTabFragment :BaseFragment() {

    private lateinit var viewModel: SearchTabViewModel
    private lateinit var binding: FragmentTabBinding
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


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentTabBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args = arguments
        if (args != null) {
            type = args.getString(Common.SearchKey.TYPE_KEY)
            keyword = args.getString(Common.SearchKey.KEY_WORD)
        }
        initView()
    }

    private fun initView() {
        viewModel = ViewModelProviders.of(this).get(SearchTabViewModel::class.java)

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
        viewModel.activities.observe(this, Observer {
            activitiesAdapter.submitList(it.data.list)
        })
    }

    private fun initGroup() {
        groupsAdapter = GroupsAdapter {

        }
        viewModel.groups.observe(this, Observer {
            groupsAdapter.submitList(it.data.list)
        })
    }

    private fun initUser() {
        usersAdapter = UsersAdapter {

        }
        viewModel.users.observe(this, Observer {
            usersAdapter.submitList(it.data.list)
        })
    }

    fun startSearch(keyword: String) {
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
}