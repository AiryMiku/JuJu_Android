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
import com.airy.juju.ui.adapter.listView.CommentsAdapter
import com.airy.juju.viewModel.activity.ActivityDetailViewModel
import com.airy.juju.viewModel.factroy.ActivityDetailViewModelFactory
import com.airy.juju.databinding.ActivityActivityDetailBinding
import com.airy.juju.util.UIUtil
import com.airy.juju.util.UserCenter
import kotlinx.android.synthetic.main.layout_app_bar.*

class ActivityDetailActivity : BaseActivity() {

    private lateinit var binding: ActivityActivityDetailBinding
    private lateinit var viewModel: ActivityDetailViewModel
    private lateinit var adapter: CommentsAdapter
    private var id: Int = 0

    override fun toSetContentView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_activity_detail)
    }

    override fun onResume() {
        super.onResume()
        viewModel.refresh()
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

        id = intent.getIntExtra(ACTIVITY_ID_KEY, 0)
        viewModel =
            ViewModelProviders.of(this, ActivityDetailViewModelFactory(id)).get(ActivityDetailViewModel::class.java)

        adapter = CommentsAdapter {
            makeToast("Comment ID -> " + it.id)
        }
        binding.list.adapter = adapter

        // like
        binding.like.setOnClickListener {
            makeToast("点赞")
        }

        // follow
        binding.btnFollow.setOnClickListener {
            when(binding.btnFollow.text) {
                "关注" -> {
                    val params = HashMap<String, Any>()
                    params["activity_id"] = id
                    params["require_user_id"] = UserCenter.getUserId()
                    viewModel.follow(params)
                }
                "已关注" -> {
                    val params = HashMap<String, Any>()
                    params["activity_id"] = id
                    params["require_user_id"] = UserCenter.getUserId()
                    viewModel.disfollow(params)
                }
            }
        }

        initRefresh()
        subscribeUI()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_activity_detail, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.delete -> {
                UIUtil.showSimpleConfirmDialog(this,
                    "删除活动",
                    "真的真的要删除活动吗？", {
                        makeSnackar(binding.linearLayout, "删除了活动")
                        finish()
                    }, {
                        makeSnackar(binding.linearLayout, "您取消了删除")
                    })
            }

            R.id.modify -> {
                val intent = Intent(this, CreateOrModifyActivityActivity::class.java)
                intent.putExtra(Common.ParamTranferKey.ACTIVITY_ID_KEY, id)
                intent.putExtra(Common.ActivityCreateOrModifyKey.TYPE_KEY, Common.ActivityCreateOrModifyKey.MODIFY_KEY)
                startActivity(intent)
            }

            R.id.leave_comment -> {
                showCommentDialog()
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
        viewModel.activity.observe(this, Observer {
            binding.activity = it.data
            binding.like.setLikeNumber(it.data.like_number)
            binding.refresh.isRefreshing = false
        })

        viewModel.comments.observe(this, Observer {
            adapter.submitList(it.data.list)
            binding.refresh.isRefreshing = false
        })

        viewModel.commentResult.observe(this, Observer {
            if (it) {
                makeToast("评论成功")
                viewModel.refresh()
            } else {
                makeToast("评论失败")
            }
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

    private fun showCommentDialog() {
        val dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder.setTitle("想说点什么？")
        val editText = EditText(this)
        editText.hint = "友好发言，创建美好互联网环境~"
        dialogBuilder.setView(editText)
        dialogBuilder
            .setPositiveButton("发送") { _, _ ->
                // dialog, which
                val params = HashMap<String, Any>()
                params["activity_id"] = id
                params["content"] = editText.text.toString()
                params["require_user_id"] = UserCenter.getUserId()
                viewModel.leaveComment(params)
            }
        dialogBuilder
            .setNegativeButton("取消") { _, _ ->
                makeSnackar(binding.linearLayout, "取消了评论")
            }
        dialogBuilder.show()
    }

}
