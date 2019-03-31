package com.airy.juju.ui.adapter.fragment

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.airy.juju.Common
import com.airy.juju.ui.fragment.search.SearchTabFragment


/**
 * Created by Airy on 2019/3/31
 * Mail: a532710813@gmail.com
 * Github: AiryMiku
 */

class SearchTabFragmentAdapter (fm: FragmentManager) : FragmentPagerAdapter(fm) {

    companion object {
        const val PAGE_COUNT = 3
    }

    override fun getItem(position: Int): Fragment {
        var type: String = Common.SearchKey.GROUP
        when(position) {
            0 -> {
                type = Common.SearchKey.GROUP
            }
            1 -> {
                type = Common.SearchKey.ACTIVITY
            }
            2 -> {
                type = Common.SearchKey.USER
            }
        }
        return SearchTabFragment.newInstance(type)
    }

    override fun getCount(): Int {
        return PAGE_COUNT
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when(position) {
            0 -> return "群组"
            1 -> return "活动"
            2 -> return "用户"
        }
        return "未指定"
    }

}