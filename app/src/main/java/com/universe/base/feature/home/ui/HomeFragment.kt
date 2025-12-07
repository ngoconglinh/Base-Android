package com.universe.base.feature.home.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.universe.base.core.base.fragment.BaseFragment
import com.universe.base.databinding.FragmentHomeBinding
import com.universe.base.feature.home.viewmodel.HomeVM
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private val viewModel: HomeVM by activityViewModel()

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHomeBinding = FragmentHomeBinding.inflate(inflater, container, false)

    override fun initEvent() {
        viewLifecycleOwner.lifecycleScope.launch {
//            viewModel.observer
        }
    }

}