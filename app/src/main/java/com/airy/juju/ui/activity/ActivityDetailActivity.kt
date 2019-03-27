package com.airy.juju.ui.activity

import android.view.Menu
import android.view.MenuItem
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.airy.juju.R
import com.airy.juju.base.BaseActivity
import com.airy.juju.databinding.ActivityActivityDetailBinding
import com.airy.juju.ui.adapter.listView.CommentsAdapter
import com.airy.juju.viewModel.activity.ActivityDetailViewModel
import com.airy.juju.viewModel.factroy.ActivityDetailViewModeFactory
import kotlinx.android.synthetic.main.layout_app_bar.*

class ActivityDetailActivity : BaseActivity() {


    companion object {
        const val ACTIVITY_ID_KEY = "ACTIVITY_ID_KEY"
    }

    private lateinit var binding: ActivityActivityDetailBinding
    private lateinit var viewModel: ActivityDetailViewModel
    private lateinit var adapter: CommentsAdapter
    private var id: Int = 0

    override fun toSetContentView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_activity_detail)
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
        viewModel = ViewModelProviders.of(this , ActivityDetailViewModeFactory(id)).get(ActivityDetailViewModel::class.java)

        adapter = CommentsAdapter {
            makeToast("Comment ID -> "+it.id)
        }
        binding.list.adapter = adapter

        initRefresh()
        subscribeUI()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_activity_detail, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId) {
            android.R.id.home -> finish()
            R.id.delete -> {

            }
            R.id.modify -> {

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

        binding.like.setOnClickListener {
            makeToast("点赞")
        }
    }

}
