package com.airy.juju.ui.fragment.home

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.airy.juju.base.BaseFragment
import com.airy.juju.databinding.FragmentHomeBinding
import com.airy.juju.ui.adapter.GroupsAdapter


class HomeFragment : BaseFragment() {

    private lateinit var viewModel: HomeViewModel
    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: GroupsAdapter

    companion object {
        fun newInstance() = HomeFragment()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
       return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        initRecycleView()
        initRefresh()
        subscribeUI()
    }

    private fun initRecycleView() {
        adapter = GroupsAdapter(this) {
                group -> makeToast(group.name)
        }
        binding.list.addOnScrollListener(object :RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {

            }

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                    val position = layoutManager.findLastVisibleItemPosition()

                    if (position == adapter.itemCount - 1) {
                        viewModel.fetchMoreGroups()
                    }
                }
            }
        })
        binding.list.adapter = adapter
    }

    private fun initRefresh() {
        binding.refresh.setOnRefreshListener {
            binding.refresh.isRefreshing = true
            viewModel.refresh()
        }
    }

    private fun subscribeUI() {
        viewModel.groups.observe(this, Observer {
            adapter.submitList(it.data.list)
            if (it.code == 0) {
                makeToast("成功拉取，总共有"+it.data.count+"个数据")
                binding.refresh.isRefreshing = false
            } else {
                makeToast("拉取失败")
            }
        })
    }

}
