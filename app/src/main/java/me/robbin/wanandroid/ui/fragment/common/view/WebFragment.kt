package me.robbin.wanandroid.ui.fragment.common.view

import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.fragment_web.*
import me.robbin.mvvmscaffold.base.DataBindingConfig
import me.robbin.mvvmscaffold.utils.StatusBarUtils
import me.robbin.mvvmscaffold.utils.setStatusBarLightMode
import me.robbin.wanandroid.BR
import me.robbin.wanandroid.R
import me.robbin.wanandroid.app.base.BaseFragment
import me.robbin.wanandroid.data.bean.ArticleBean
import me.robbin.wanandroid.databinding.FragmentWebBinding
import me.robbin.wanandroid.ext.addTopPadding
import me.robbin.wanandroid.ext.nav
import me.robbin.wanandroid.ui.fragment.common.viewmodel.WebViewModel

/**
 * 网页 Fragment
 * Create by Robbin at 2020/7/11
 */
class WebFragment : BaseFragment<WebViewModel, FragmentWebBinding>() {

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.fragment_web, BR.state, appViewModel)
            .addBindingParams(BR.click, WebClick())
    }

    // 文章信息
    private var article: ArticleBean? = null

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
        setStatusBarLightMode(true)
        arguments?.let {
            article = it.getParcelable("article")
        }
        mBinding.article = article!!
        statusWeb.addTopPadding(StatusBarUtils.getStatusBarHeight())
        webDetail.loadUrl(article?.link!!)
        webDetail.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                view?.loadUrl(url!!)
                return true
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        setStatusBarLightMode(false)
    }

    inner class WebClick {
        fun back() {
            nav().navigateUp()
        }
    }

}