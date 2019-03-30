package com.airy.juju.ui.activity

import android.content.Intent
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.airy.juju.Common
import com.airy.juju.R
import com.airy.juju.base.BaseActivity
import com.airy.juju.databinding.ActivityItemListBinding
import com.airy.juju.ui.adapter.listView.ActivitiesAdapter
import com.airy.juju.ui.adapter.listView.GroupsAdapter
import com.airy.juju.util.UserCenter
import com.airy.juju.viewModel.activity.ItemListViewModel

class ItemListActivity : BaseActivity() {

    private lateinit var activitiesAdapter: ActivitiesAdapter
    private lateinit var groupsAdapter: GroupsAdapter
    private lateinit var viewModel: ItemListViewModel
    private lateinit var binding: ActivityItemListBinding

    override fun toSetContentView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_item_list)
    }

    override fun initViews() {
        super.initViews()
        initToolBar(true,true,true)

        typeControl()
        subscribeUI()
    }

    private fun typeControl() {
        val itn = intent
        val type = itn.getStringExtra(Common.ItemListTypeKey.TYPE_KEY)
        when(type) {
            Common.ItemListTypeKey.MY_ACTIVITY -> {
                setToolBarTitle("我的活动")
                activitiesAdapter = ActivitiesAdapter {
                    val intent = Intent(this, ActivityDetailActivity::class.java)
                    intent.putExtra(Common.ParamTranferKey.ACTIVITY_ID_KEY, it.id)
                    startActivity(intent)
                }
                binding.list.adapter = activitiesAdapter
                val params = HashMap<String, Any>()
                params["user_id"] = UserCenter.getUserId()
                params["page"] = 1
                params["size"] = 99
                viewModel.fetchActivity(params)
            }
            Common.ItemListTypeKey.MY_GROUP -> {
                setToolBarTitle("我的群组")
                groupsAdapter = GroupsAdapter {
                    val intent = Intent(this, GroupDetailActivity::class.java)
                    intent.putExtra(Common.ParamTranferKey.GROUP_ID_KEY, it.id)
                    startActivity(intent)
                }
                binding.list.adapter = groupsAdapter
                val params = HashMap<String, Any>()
                params["user_id"] = UserCenter.getUserId()
                params["page"] = 1
                params["size"] = 99
                viewModel.fetchFollowGroups(params)
            }
        }
    }

    private fun subscribeUI() {
        viewModel.activities.observe(this, Observer {
            activitiesAdapter.submitList(it.data.list)
        })

        viewModel.groups.observe(this, Observer {
            groupsAdapter.submitList(it.data.list)
        })
    }
}
