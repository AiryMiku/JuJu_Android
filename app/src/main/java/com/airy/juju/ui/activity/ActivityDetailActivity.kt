package com.airy.juju.ui.activity

import android.view.Menu
import android.view.MenuItem
import com.airy.juju.R
import com.airy.juju.base.BaseActivity
import kotlinx.android.synthetic.main.layout_app_bar.*

class ActivityDetailActivity : BaseActivity() {

    override fun setContentViewId(): Int {
        return R.layout.activity_activity_detail
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
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_activity_detail, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

}
