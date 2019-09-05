package com.airy.juju.base

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import androidx.annotation.Nullable
import dagger.android.support.DaggerFragment


/**
 * Created by Airy on 2019/3/11
 * Mail: a532710813@gmail.com
 * Github: AiryMiku
 */

abstract class BaseFragment : DaggerFragment() {

    private var isPrepared: Boolean = false
    private var isFirst: Boolean = true
    private var isVisi: Boolean = false

    private lateinit var mContext: Context
    private lateinit var mRootView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = this.activity!!
    }

    override fun onResume() {
        super.onResume()
        if (userVisibleHint) {
            userVisibleHint = true
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        isPrepared = true
        initPrepare()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mRootView = initView(inflater, container, savedInstanceState)
        return mRootView
    }

    // lazy
    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (userVisibleHint) {
            isVisi = true
            lazyLoad()
        } else {
            isVisi = false
            onInvisible()
        }
    }

    /**
     * 在onActivityCreated中调用的方法，可以用来进行初始化操作。
     */
    protected abstract fun initPrepare()

    /**
     * fragment被设置为不可见时调用
     */
    protected abstract fun onInvisible()

    /**
     * 这里获取数据，刷新界面
     */
    protected abstract fun initData()

    /**
     * 初始化布局，请不要把耗时操作放在这个方法里，这个方法用来提供一个
     * 基本的布局而非一个完整的布局，以免ViewPager预加载消耗大量的资源。
     */
    protected abstract fun initView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View

    protected fun lazyLoad() {
        if (!isPrepared || !isVisible || !isFirst) {
            // isPrepared 父activity
            // isVisible 可见性
            // isFirst 切换加载判断
            return
        }
        initData()
        isFirst = false
    }

    fun makeToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}