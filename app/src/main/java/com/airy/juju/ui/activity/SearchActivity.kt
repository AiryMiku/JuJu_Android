package com.airy.juju.ui.activity


import androidx.databinding.DataBindingUtil
import androidx.viewpager.widget.ViewPager
import com.airy.juju.Common
import com.airy.juju.R
import com.airy.juju.base.BaseActivity
import com.airy.juju.databinding.ActivitySearchBinding
import com.airy.juju.ui.adapter.fragment.SearchTabFragmentAdapter

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

        binding.viewPager.addOnPageChangeListener(object :ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onPageSelected(position: Int) {

            }

        })

    }
}
