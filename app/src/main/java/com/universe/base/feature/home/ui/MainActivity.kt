package com.universe.base.feature.home.ui

import android.view.LayoutInflater
import com.universe.base.core.base.activity.BaseVMActivity
import com.universe.base.databinding.ActivityMainBinding
import com.universe.base.feature.home.viewmodel.HomeVM
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity() : BaseVMActivity<ActivityMainBinding, HomeVM>() {

    override val viewModel: HomeVM by viewModel()

    override fun inflateBinding(layoutInflater: LayoutInflater): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun initView() {

    }

    override fun initData() {

    }

    override fun initEvent() {

    }

}