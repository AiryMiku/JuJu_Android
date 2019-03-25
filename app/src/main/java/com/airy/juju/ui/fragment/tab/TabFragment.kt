package com.airy.juju.ui.fragment.tab

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.airy.juju.base.BaseFragment
import com.airy.juju.databinding.FragmentTabBinding


/**
 * Created by Airy on 2019/3/25
 * Mail: a532710813@gmail.com
 * Github: AiryMiku
 */

class TabFragment :BaseFragment() {

//    private lateinit var viewModel
    private lateinit var binding: FragmentTabBinding
//    private lateinit var adapter


    private lateinit var args:String
    private var userId: Int? = null

    companion object {

        val TAG = "TAG"
        val ID = "ID"

        fun newInstance(tag: String,userId: Int): TabFragment {
            val args = Bundle()
            args.putString(TAG, tag)
            args.putInt(ID, userId)
            val fragment = TabFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        args = arguments!!.getString(TabFragment.TAG)
        userId = arguments!!.getInt(TabFragment.ID)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentTabBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}