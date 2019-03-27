package com.airy.juju.ui.activity

import android.content.DialogInterface
import android.content.Intent
import android.text.Editable
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.airy.juju.R
import com.airy.juju.base.BaseActivity
import com.airy.juju.databinding.ActivityGroupDetailBinding
import com.airy.juju.ui.adapter.listView.ActivitiesAdapter
import com.airy.juju.viewModel.activity.GroupDetailViewModel
import com.airy.juju.viewModel.factroy.GroupDetailViewModelFactory
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.layout_app_bar.*

class GroupDetailActivity : BaseActivity() {

    companion object {
        const val GROUP_ID_KEY = "GROUP_ID_KEY"
    }
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
            intent.putExtra(ActivityDetailActivity.ACTIVITY_ID_KEY, activity.id)
            startActivity(intent)
        }
        binding.list.adapter = adapter

        id = intent.getIntExtra(GROUP_ID_KEY,0)
        viewModel = ViewModelProviders.of(this, GroupDetailViewModelFactory(id)).get(GroupDetailViewModel::class.java)

        initRefresh()
        subscribeUI()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_group_detail, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId) {
            android.R.id.home -> finish()

            R.id.create -> {
                val intent = Intent(this, CreateOrModifyGroupActivity::class.java)
                intent.putExtra(CreateOrModifyGroupActivity.TYPE_KEY, CreateOrModifyGroupActivity.CREATE_KEY)
                startActivity(intent)
            }

            R.id.modify -> {
                val intent = Intent(this, CreateOrModifyGroupActivity::class.java)
                intent.putExtra(GROUP_ID_KEY, id)
                intent.putExtra(CreateOrModifyGroupActivity.TYPE_KEY, CreateOrModifyGroupActivity.MODIFY_KEY)
                startActivity(intent)
            }

            R.id.send_notice -> showSendNoticeDialog()
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
        })
        viewModel.groupActivities.observe(this, Observer {
            adapter.submitList(it.data.list)
            binding.refresh.isRefreshing = false
        })
    }

    private fun showSendNoticeDialog() {
        val dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder.setTitle("编辑什么样的公告呢？")
        val editText = EditText(this)
        editText.setText(binding.group.notice)
        dialogBuilder.setView(editText)
        dialogBuilder
            .setPositiveButton("发送") { _, _ -> // dialog, which
                makeSnackar(binding.linearLayout, "编辑公告成功")
            }
        dialogBuilder
            .setNegativeButton("取消") { _, _ ->
                makeSnackar(binding.linearLayout, "您取消了编辑公告")
            }
        dialogBuilder.show()
    }
}
