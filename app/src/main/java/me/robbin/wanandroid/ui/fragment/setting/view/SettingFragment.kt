package me.robbin.wanandroid.ui.fragment.setting.view

import android.os.Bundle
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.fragment_setting.*
import me.robbin.mvvmscaffold.base.DataBindingConfig
import me.robbin.wanandroid.BR
import me.robbin.wanandroid.R
import me.robbin.wanandroid.app.base.BaseFragment
import me.robbin.wanandroid.app.utils.CacheUtils
import me.robbin.wanandroid.app.utils.isNightMode
import me.robbin.wanandroid.app.utils.setNightMode
import me.robbin.wanandroid.databinding.FragmentSettingBinding
import me.robbin.wanandroid.ext.nav
import me.robbin.wanandroid.ui.fragment.setting.viewmodel.SettingViewModel

/**
 * 设置 Fragment
 * Create by Robbin at 2020/7/20
 */
class SettingFragment : BaseFragment<SettingViewModel, FragmentSettingBinding>() {

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.fragment_setting, BR.state, mViewModel)
            .addBindingParams(BR.appState, appViewModel)
            .addBindingParams(BR.click, SettingClick())
    }

    override fun initView(savedInstanceState: Bundle?) {
        switchNight.isChecked = isNightMode(requireContext())
        switchNight.setOnCheckedChangeListener { _, checked ->
            setNightMode(checked)
            appViewModel.isNightMode.value = checked
            CacheUtils.setNightMode(checked)
        }
    }

    override fun createObserver() {
        mViewModel.back.observe(viewLifecycleOwner, Observer {
            if (it) nav().navigateUp()
        })
    }

    inner class SettingClick {
        fun back() {
            nav().navigateUp()
        }

        fun logOut() {
            back()
            appViewModel.clearUser()
        }
    }

}