package com.airy.juju.ui.fragment.tab

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListAdapter
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

class TabFragment :BaseFragment() {

    private lateinit var viewModel: TabViewModel
    private lateinit var binding: FragmentTabBinding
    private lateinit var activitiesAdapter: ActivitiesAdapter
    private lateinit var groupsAdapter: GroupsAdapter
    private lateinit var usersAdapter: UsersAdapter
    private lateinit var type: String

    companion object {

        fun newInstance(type: String): TabFragment {
            val args = Bundle()
            args.putString(Common.TabFragmentTypeKey.TYPE_KEY, type)
            val fragment = TabFragment()
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
        if (args != null){
            type = args.getString(Common.TabFragmentTypeKey.TYPE_KEY)
        }
        init()
    }

    private fun init() {
        viewModel = ViewModelProviders.of(this).get(TabViewModel::class.java)

        when(type) {
            Common.TabFragmentTypeKey.ACTIVITY -> {
                initActivity()
            }
            Common.TabFragmentTypeKey.GROUP -> {
                initGroup()
            }
            Common.TabFragmentTypeKey.USER -> {
                initUser()
            }
        }
    }

    private fun initActivity() {
        activitiesAdapter = ActivitiesAdapter {

        }
    }

    private fun initGroup() {
        groupsAdapter = GroupsAdapter {

        }
    }

    private fun initUser() {
        usersAdapter = UsersAdapter {

        }
    }
}