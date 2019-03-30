package com.airy.juju.ui.fragment.me

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.airy.juju.Common
import com.airy.juju.base.BaseFragment
import com.airy.juju.databinding.FragmentMeBinding
import com.airy.juju.ui.activity.CreateOrModifyGroupActivity


/**
 * Created by Airy on 2019/3/15
 * Mail: a532710813@gmail.com
 * Github: AiryMiku
 */

class MeFragment: BaseFragment() {

    private lateinit var binding: FragmentMeBinding
    private lateinit var viewModel: MeViewModel

    companion object {
        fun getInstance() = MeFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProviders.of(this).get(MeViewModel::class.java)
        initViews()
    }

    private fun initViews() {
        binding.createGroup.setOnClickListener {
            val intent = Intent(activity, CreateOrModifyGroupActivity::class.java)
            intent.putExtra(Common.ActivityCreateOrModifyKey.TYPE_KEY, Common.ActivityCreateOrModifyKey.CREATE_KEY)
            startActivity(intent)
        }



    }
}