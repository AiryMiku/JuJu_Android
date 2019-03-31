package com.airy.juju.ui.activity


import android.app.ProgressDialog
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import com.airy.juju.R
import com.airy.juju.base.BaseActivity
import com.airy.juju.databinding.ActivitySearchBinding
import com.airy.juju.eventBus.MessageEvent
import com.airy.juju.ui.adapter.fragment.SearchTabFragmentAdapter
import org.greenrobot.eventbus.EventBus

class SearchActivity : BaseActivity() {

    private lateinit var binding: ActivitySearchBinding

    override fun toSetContentView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search)
    }

    override fun initViews() {
        super.initViews()
        val adapter = SearchTabFragmentAdapter(supportFragmentManager)
        binding.viewPager.adapter = adapter
        binding.tabLayout.setupWithViewPager(binding.viewPager)



        binding.search.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    EventBus.getDefault().post(MessageEvent(query))
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

}
