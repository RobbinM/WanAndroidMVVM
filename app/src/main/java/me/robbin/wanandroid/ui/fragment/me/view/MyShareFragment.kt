package me.robbin.wanandroid.ui.fragment.me.view

import me.robbin.mvvmscaffold.base.DataBindingConfig
import me.robbin.wanandroid.BR
import me.robbin.wanandroid.R
import me.robbin.wanandroid.databinding.FragmentMyShareBinding
import me.robbin.wanandroid.ui.fragment.common.view.BaseArticlesFragment
import me.robbin.wanandroid.ui.fragment.common.viewmodel.ArticleListViewModel

/**
 * 我的分享 Fragment
 * Create by Robbin at 2020/7/26
 */
class MyShareFragment : BaseArticlesFragment<ArticleListViewModel, FragmentMyShareBinding>() {

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.fragment_my_share, BR.viewModel, mViewModel)
    }

}