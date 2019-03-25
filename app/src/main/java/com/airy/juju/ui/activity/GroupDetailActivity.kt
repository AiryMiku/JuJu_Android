package com.airy.juju.ui.activity

import android.view.Menu
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.airy.juju.R
import com.airy.juju.base.BaseActivity
import com.airy.juju.databinding.ActivityGroupDetailBinding
import com.airy.juju.ui.adapter.listView.ActivitiesAdapter
import com.airy.juju.viewModel.activity.GroupDetailViewModel
import com.airy.juju.viewModel.factroy.GroupDetailViewModelFactory
import kotlinx.android.synthetic.main.layout_app_bar.*

class GroupDetailActivity : BaseActivity() {

    companion object {
        const val GROUP_ID_KEY = "GROUP_ID_KEY"
    }
    private lateinit var binding: ActivityGroupDetailBinding
    private lateinit var viewModel: GroupDetailViewModel
    private lateinit var adapter: ActivitiesAdapter

    override fun toSetContentView() {
        binding = DataBindingUtil.setContentView(this,R.layout.activity_group_detail)
    }

    override fun loadData() {
        super.loadData()
        val id = intent.getIntExtra(GROUP_ID_KEY,0)
        viewModel = ViewModelProviders.of(this, GroupDetailViewModelFactory(id)).get(GroupDetailViewModel::class.java)
        initRefresh()
        subscribeUI()
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

        // rv
        adapter = ActivitiesAdapter { activity ->
            makeToast(activity.title)
        }
        binding.list.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_group_detail, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId) {
            android.R.id.home -> finish()
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
}
