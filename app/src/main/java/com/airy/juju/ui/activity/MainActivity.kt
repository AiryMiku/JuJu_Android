package com.airy.juju.ui.activity


import android.support.v4.view.ViewPager
import com.airy.juju.R
import com.airy.juju.base.BaseActivity
import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.ashokvarma.bottomnavigation.BottomNavigationItem
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    private lateinit var viewPager: ViewPager
    private lateinit var bottomNavigationBar: BottomNavigationBar

    override fun setContentViewId(): Int {
        return R.layout.activity_main
    }

    override fun loadData() {

    }



    override fun initViews() {
        // bottombar style
        bottomNavigationBar = main_bottom_bar
        bottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_RIPPLE)
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_SHIFTING)
        bottomNavigationBar.setBarBackgroundColor(R.color.white)
        bottomNavigationBar.addItem(BottomNavigationItem(R.drawable.ic_timeline_24dp,"时间线").setActiveColorResource(R.color.timeLineBlue))
            .addItem(BottomNavigationItem(R.drawable.ic_notifications_24dp,"通知").setActiveColorResource(R.color.notificationOrange))
            .addItem(BottomNavigationItem(R.drawable.ic_chat_24dp,"聊天").setActiveColorResource(R.color.ChatPink))
            .addItem(BottomNavigationItem(R.drawable.ic_me_circle_24dp,"我").setActiveColorResource(R.color.MePurple))
            .setFirstSelectedPosition(0)
            .initialise()
    }
}
