package com.airy.juju.ui.activity

import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.airy.juju.Common
import com.airy.juju.Common.ParamTranferKey.ACTIVITY_ID_KEY
import com.airy.juju.R
import com.airy.juju.base.BaseActivity
import com.airy.juju.bean.User
import com.airy.juju.databinding.ActivityGroupDetailBinding
import com.airy.juju.ui.adapter.listView.ActivitiesAdapter
import com.airy.juju.util.UIUtil
import com.airy.juju.util.UserCenter
import com.airy.juju.viewModel.activity.GroupDetailViewModel
import com.airy.juju.viewModel.factroy.GroupDetailViewModelFactory
import kotlinx.android.synthetic.main.layout_app_bar.*

class GroupDetailActivity : BaseActivity() {

    private lateinit var binding: ActivityGroupDetailBinding
    private lateinit var viewModel: GroupDetailViewModel
    private lateinit var adapter: ActivitiesAdapter
    private var id: Int = 0

    override fun toSetContentView() {
        binding = DataBindingUtil.setContentView(this,R.layout.activity_group_detail)
    }

    override fun initViews() {
        super.initViews()
        setSupportActionBar(toolbar)
        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true)
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setDisplayShowTitleEnabled(false)
        }
        // 解决滑动冲突
        binding.scrollView.viewTreeObserver.addOnScrollChangedListener {
            binding.refresh.isEnabled = (binding.scrollView.scrollY == 0)
        }

        // rv onclikc callback
        adapter = ActivitiesAdapter { activity ->
            makeToast("Activity ID -> "+activity.id)
            val intent = Intent(this, ActivityDetailActivity::class.java)
            intent.putExtra(ACTIVITY_ID_KEY, activity.id)
            startActivity(intent)
        }
        binding.list.adapter = adapter

        id = intent.getIntExtra(Common.ParamTranferKey.GROUP_ID_KEY,0)
        viewModel = ViewModelProviders.of(this, GroupDetailViewModelFactory(id)).get(GroupDetailViewModel::class.java)

        // follow
        binding.btnFollow.setOnClickListener {
            when(binding.btnFollow.text) {
                "关注" -> {
                    val params = HashMap<String, Any>()
                    params["group_id"] = id
                    params["require_user_id"] = UserCenter.getUserId()
                    viewModel.follow(params)
                }
                "已关注" -> {
                    val params = HashMap<String, Any>()
                    params["group_id"] = id
                    params["require_user_id"] = UserCenter.getUserId()
                    viewModel.disfollow(params)
                }
            }
        }

        initRefresh()
        subscribeUI()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_group_detail, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId) {
            R.id.create_actitity -> {
                val intent = Intent(this, CreateOrModifyActivityActivity::class.java)
                intent.putExtra(Common.ParamTranferKey.GROUP_ID_KEY, id)
                intent.putExtra(Common.ActivityCreateOrModifyKey.TYPE_KEY, Common.ActivityCreateOrModifyKey.CREATE_KEY)
                startActivity(intent)
            }

            R.id.modify -> {
                val intent = Intent(this, CreateOrModifyGroupActivity::class.java)
                intent.putExtra(Common.ParamTranferKey.GROUP_ID_KEY, id)
                intent.putExtra(Common.ActivityCreateOrModifyKey.TYPE_KEY, Common.ActivityCreateOrModifyKey.MODIFY_KEY)
                startActivity(intent)
            }
            R.id.send_notice -> showSendNoticeDialog()
            R.id.delete -> {
                showDeleteDialog()
            }

            R.id.invite_people -> {}
//            R.id.remove_people -> {}
//            R.id.set_admin -> {}
            R.id.send_message -> {
                val itn = Intent(this, ChatActivity::class.java)
                itn.putExtra(Common.ChatEnterType.KEY, Common.ChatEnterType.FROM_GROUP)
                itn.putExtra(Common.ParamTranferKey.GROUP_ID_KEY, id)
                startActivity(itn)
            }
            R.id.show_member -> {
                val itn = Intent(this, ItemListActivity::class.java)
                itn.putExtra(Common.ItemListTypeKey.TYPE_KEY, Common.ItemListTypeKey.USER)
                itn.putExtra(Common.ParamTranferKey.GROUP_ID_KEY, id)
                startActivity(itn)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initRefresh() {
        binding.refresh.setOnRefreshListener {
            binding.refresh.isRefreshing = true
            viewModel.refresh()
        }

    }

    private fun subscribeUI() {
        viewModel.group.observe(this, Observer {
            binding.group = it.data
            binding.refresh.isRefreshing = false
            if (it.data.is_follow) {
                binding.btnFollow.text = "已关注"
            }
        })
        viewModel.groupActivities.observe(this, Observer {
            adapter.submitList(it.data.list)
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

        viewModel.deleteResult.observe(this , Observer {
            if (it) {
                makeToast("操作成功")
                finish()
            } else {
                makeToast("操作失败")
            }
        })

        viewModel.modifyNoticeResult.observe(this, Observer {
            if (it) {
                makeSnackar(binding.linearLayout, "编辑公告成功")
                viewModel.refresh()
            } else {
                makeSnackar(binding.linearLayout, "编辑公告失败")
            }
        })
    }

    private fun showSendNoticeDialog() {
        val dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder.setTitle("编辑什么样的公告呢？")
        val editText = EditText(this)
        editText.setText(binding.group?.notice)
        dialogBuilder.setView(editText)
        dialogBuilder
            .setPositiveButton("发送") { _, _ -> // dialog, which
                val params = HashMap<String, Any>()
                params["group_id"] = id
                params["notice"] = editText.text.toString()
                viewModel.modifyNotice(params)
            }
        dialogBuilder
            .setNegativeButton("取消") { _, _ ->
                makeSnackar(binding.linearLayout, "您取消了编辑公告")
            }
        dialogBuilder.show()
    }

    private fun showDeleteDialog() {
        val dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder.setTitle("删除群组确认")
        dialogBuilder.setMessage("真的真的要删除群组？")
        dialogBuilder
            .setPositiveButton("确认") { _, _ -> // dialog, which
                val param = HashMap<String, Any>()
                param["group_id"] = id
                param["access_token"] = UserCenter.getUserToken()
                viewModel.deleteGroup(param)
            }
        dialogBuilder
            .setNegativeButton("取消") { _, _ ->
                makeSnackar(binding.linearLayout, "取消了删除")
            }
        dialogBuilder.show()
    }

    override fun onResume() {
        super.onResume()
        viewModel.refresh()
    }
}
