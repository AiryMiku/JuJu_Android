package com.airy.juju.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter


/**
 * Created by Airy on 2019/3/14
 * Mail: a532710813@gmail.com
 * Github: AiryMiku
 */

class MainFragmentAdapter(fm: FragmentManager, private val fragments: List<Fragment>) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun getCount(): Int {
        return fragments.count()
    }

}