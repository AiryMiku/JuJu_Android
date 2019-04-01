package com.airy.juju.ui.activity


import android.view.Menu
import android.view.MenuItem
import androidx.viewpager.widget.ViewPager
import com.airy.juju.R
import com.airy.juju.base.BaseActivity
import com.airy.juju.ui.adapter.fragment.MainFragmentAdapter
import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.ashokvarma.bottomnavigation.BottomNavigationItem
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_app_bar.*

class MainActivity : BaseActivity() {

    private lateinit var viewPager: ViewPager
    private lateinit var bottomNavigationBar: BottomNavigationBar

    override fun toSetContentView() {
        setContentView(R.layout.activity_main)
    }

    override fun initViews() {
        super.initViews()
        initToolBar(false,false,true)
        initBottomNavigationBar()
        initViewPager()
    }

    private fun initBottomNavigationBar() {
        bottomNavigationBar = main_bottom_bar
        bottomNavigationBar.setTabSelectedListener(object : BottomNavigationBar.OnTabSelectedListener {
            override fun onTabReselected(position: Int) {
                // 重复选中
            }
            override fun onTabUnselected(position: Int) {
                // 选中 -> 未选中
            }
            override fun onTabSelected(position: Int) {
                // 未选中 -> 选中
                viewPager.currentItem = position
                when(position) {
                    0 -> { setToolBarTitle("广场") }
                    1 -> { setToolBarTitle("群组") }
                    2 -> { setToolBarTitle("通知") }
                    3 -> { setToolBarTitle("聊天") }
                    4 -> { setToolBarTitle("我") }
                }
            }
        })
        bottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_RIPPLE)
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_SHIFTING)
        bottomNavigationBar.setBarBackgroundColor(R.color.white)
        bottomNavigationBar.addItem(BottomNavigationItem(R.drawable.ic_timeline_24dp,"广场").setActiveColorResource(R.color.PlayGroundBlue))
            .addItem(BottomNavigationItem(R.drawable.ic_group_24dp,"群组").setActiveColorResource(R.color.GroupGreen))
            .addItem(BottomNavigationItem(R.drawable.ic_notifications_24dp,"通知").setActiveColorResource(R.color.notificationOrange))
            .addItem(BottomNavigationItem(R.drawable.ic_chat_24dp,"聊天").setActiveColorResource(R.color.ChatPink))
            .addItem(BottomNavigationItem(R.drawable.ic_me_circle_24dp,"我").setActiveColorResource(R.color.MeCickPink))
            .setFirstSelectedPosition(0)
            .initialise()
        toolbar.title = "广场"
    }

    private fun initViewPager() {
        viewPager = view_pager
        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(p0: Int) { }
            override fun onPageScrolled(p0: Int, p1: Float, p2: Int) { }
            override fun onPageSelected(p0: Int) {
                // 滑动切换
                bottomNavigationBar.selectTab(p0)
            }
        })
        val fragmentAdapter = MainFragmentAdapter(supportFragmentManager)
        viewPager.adapter = fragmentAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId) {
            R.id.search -> {
                activityIntentTo(SearchActivity::class.java)
            }
        }
        return super.onOptionsItemSelected(item)
    }

}
