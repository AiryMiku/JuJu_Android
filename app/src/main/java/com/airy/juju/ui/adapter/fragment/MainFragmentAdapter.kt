package com.airy.juju.ui.adapter.fragment

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.airy.juju.ui.fragment.chat.ChatFragment
import com.airy.juju.ui.fragment.group.GroupFragment
import com.airy.juju.ui.fragment.me.MeFragment
import com.airy.juju.ui.fragment.notifcation.NotificationFragment
import com.airy.juju.ui.fragment.playground.PlayGroundFragment


/**
 * Created by Airy on 2019/3/14
 * Mail: a532710813@gmail.com
 * Github: AiryMiku
 */

class MainFragmentAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    companion object {
        const val PAGE_COUNT = 5
    }

    override fun getItem(position: Int): Fragment {
        when(position) {
            0 -> { return PlayGroundFragment.newInstance()}
            1 -> { return GroupFragment.newInstance() }
            2 -> { return NotificationFragment.newInstance() }
            3 -> { return ChatFragment.newInstance() }
            4 -> { return MeFragment.newInstance() }
        }
        return GroupFragment.newInstance()
    }

    override fun getCount(): Int {
        return PAGE_COUNT
    }

}