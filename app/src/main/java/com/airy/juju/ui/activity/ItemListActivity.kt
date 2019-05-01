package com.airy.juju.ui.activity

import android.content.Intent
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.airy.juju.Common
import com.airy.juju.R
import com.airy.juju.base.BaseActivity
import com.airy.juju.bean.User
import com.airy.juju.databinding.ActivityItemListBinding
import com.airy.juju.ui.adapter.listView.ActivitiesAdapter
import com.airy.juju.ui.adapter.listView.GroupsAdapter
import com.airy.juju.ui.adapter.listView.UsersAdapter
import com.airy.juju.util.UserCenter
import com.airy.juju.viewModel.activity.ItemListViewModel

class ItemListActivity : BaseActivity() {

    private lateinit var activitiesAdapter: ActivitiesAdapter
    private lateinit var groupsAdapter: GroupsAdapter
    private lateinit var usersAdapter: UsersAdapter
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

    override fun loadData() {
        super.loadData()
        viewModel = ViewModelProviders.of(this).get(ItemListViewModel::class.java)
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
                binding.refresh.setOnRefreshListener {
                    viewModel.fetchActivity(params)
                }
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
                binding.refresh.setOnRefreshListener {
                    viewModel.fetchFollowGroups(params)
                }
            }
            Common.ItemListTypeKey.USER -> {
                setToolBarTitle("人员列表")
                usersAdapter = UsersAdapter {
//                    val intent = Intent(this, UserDetailActivity::class.java)
//                    intent.putExtra(Common.ParamTranferKey.USER_ID_KEY, it.id)
//                    startActivity(intent)
                    showGroupMemberListDialog(it)
                }
                binding.list.adapter = usersAdapter
                val params = HashMap<String, Any>()
                val groupId = intent.getIntExtra(Common.ParamTranferKey.GROUP_ID_KEY, 0)
                params["group_id"] = groupId
                params["page"] = 1
                params["size"] = 99
                viewModel.fetchMemberInGroup(params)
                binding.refresh.setOnRefreshListener {
                    viewModel.fetchMemberInGroup(params)
                }
            }
            Common.ItemListTypeKey.MY_FOLLOW_USER -> {
                setToolBarTitle("关注的人")
                usersAdapter = UsersAdapter {
                    val intent = Intent(this, UserDetailActivity::class.java)
                    intent.putExtra(Common.ParamTranferKey.USER_ID_KEY, it.id)
                    startActivity(intent)
                }
                binding.list.adapter = usersAdapter
                val params = HashMap<String, Any>()
                params["access_token"] = UserCenter.getUserToken()
                params["page"] = 1
                params["size"] = 99
                viewModel.fetchFollowUsers(params)
                binding.refresh.setOnRefreshListener {
                    viewModel.fetchFollowUsers(params)
                }
            }
        }
    }

    private fun subscribeUI() {
        viewModel.activities.observe(this, Observer {
            activitiesAdapter.submitList(it.data.list)
            binding.refresh.isRefreshing = false
        })

        viewModel.groups.observe(this, Observer {
            groupsAdapter.submitList(it.data.list)
            binding.refresh.isRefreshing = false
        })

        viewModel.users.observe(this, Observer {
            usersAdapter.submitList(it.data.list)
            binding.refresh.isRefreshing = false
        })

        viewModel.removeMemberResult.observe(this, Observer {
            if (it) {
                makeToast("移除成功")
            } else {
                makeToast("移除失败")
            }
        })
    }

    // group detail setting
    private fun showGroupMemberListDialog(user: User) {
        val itemStrings: Array<String> = arrayOf("查看资料","设置管理员","移除人员")
        val listDialog = AlertDialog.Builder(this)
        listDialog.setItems(itemStrings) { _, which ->
            when(which) {
                0 -> {
                    val intent = Intent(this, UserDetailActivity::class.java)
                    intent.putExtra(Common.ParamTranferKey.USER_ID_KEY, user.id)
                    startActivity(intent)
                }
                1 -> {

                }
                2 -> {
                    showRemoveMemberDialog(user)
                }
            }
        }
        listDialog.show()
    }

    private fun showRemoveMemberDialog(user: User) {
        val dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder.setMessage("真的真的要移除？")
        dialogBuilder
            .setPositiveButton("确认") { _, _ -> // dialog, which
                val param = HashMap<String, Any>()
                val groupId = intent.getIntExtra(Common.ParamTranferKey.GROUP_ID_KEY, 0)
                param["group_id"] = groupId
                param["require_user_id"] = user.id
                viewModel.removeGroupMember(param)
            }
        dialogBuilder
            .setNegativeButton("取消") { _, _ ->
                makeToast("已取消")
            }
        dialogBuilder.show()
    }
}
