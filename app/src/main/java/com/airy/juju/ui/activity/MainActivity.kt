package com.airy.juju.ui.activity


import androidx.viewpager.widget.ViewPager
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

        bottomNavigationBar.setTabSelectedListener(object : BottomNavigationBar.OnTabSelectedListener {
            override fun onTabReselected(position: Int) {
                // 重复选中
            }

            override fun onTabUnselected(position: Int) {
                // 选中 -> 未选中
            }

            override fun onTabSelected(position: Int) {
                // 未选中 -> 选中
                when(position) {
                    0 -> {

                    }
                    1 -> {

                    }
                    2 -> {

                    }
                    3 -> {

                    }
                }
            }
        })


        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(p0: Int) { }

            override fun onPageScrolled(p0: Int, p1: Float, p2: Int) { }

            override fun onPageSelected(p0: Int) {
                // 滑动切换
                bottomNavigationBar.selectTab(p0)
            }
        })
    }
}
