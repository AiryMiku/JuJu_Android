package com.airy.juju.ui.activity


import android.view.Menu
import android.view.ViewGroup
import android.widget.LinearLayout
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
    private lateinit var searchView: SearchView

    override fun toSetContentView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search)
    }

    override fun initViews() {
        super.initViews()
        initToolBar(false, true, false)
        val adapter = SearchTabFragmentAdapter(supportFragmentManager)
        binding.viewPager.adapter = adapter
        binding.tabLayout.setupWithViewPager(binding.viewPager)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)
        val searchItem = menu?.findItem(R.id.search)
        searchView = searchItem?.actionView as SearchView
        searchView.isIconified = true
        val searchAutoComplete = searchView.findViewById<SearchView.SearchAutoComplete>(R.id.search_src_text)
        searchAutoComplete.setTextColor(resources.getColor(R.color.black))
        searchView.queryHint = "请输入要搜索的关键字"
        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
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
        searchView.setIconifiedByDefault(false)
        searchView.isSubmitButtonEnabled = true
        searchView.onActionViewExpanded()
        val searchEditFrame = searchView.findViewById<LinearLayout>(R.id.search_edit_frame)
        val marginParams = searchEditFrame.layoutParams as ViewGroup.MarginLayoutParams
        marginParams.leftMargin = 0
        marginParams.rightMargin = 0
        searchEditFrame.layoutParams = marginParams
        return super.onCreateOptionsMenu(menu)
    }
}
