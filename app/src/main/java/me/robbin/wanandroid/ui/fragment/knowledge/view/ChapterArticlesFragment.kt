package me.robbin.wanandroid.ui.fragment.knowledge.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_chapter_articles.*
import me.robbin.mvvmscaffold.base.fragment.BaseVMFragment
import me.robbin.mvvmscaffold.utils.StatusBarUtils
import me.robbin.wanandroid.R
import me.robbin.wanandroid.data.bean.ChapterBean
import me.robbin.wanandroid.ext.addTopPadding
import me.robbin.wanandroid.ext.init
import me.robbin.wanandroid.ext.nav
import me.robbin.wanandroid.ui.fragment.common.view.ArticleListsFragment
import me.robbin.wanandroid.ui.fragment.common.view.ArticleType
import me.robbin.wanandroid.ui.fragment.knowledge.viewmodel.ChapterListViewModel

/**
 * 模块文章列表 Fragment
 * Create by Robbin at 2020/7/15
 */
class ChapterArticlesFragment : BaseVMFragment<ChapterListViewModel>() {

    override fun getLayoutRes(): Int = R.layout.fragment_chapter_articles

    private lateinit var chapter: ChapterBean
    private var index: Int = 1

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
        arguments?.let {
            chapter = it.getParcelable("data")!!
            index = it.getInt("index")
        }
        toolbarChapterArticles.title = chapter.name
        toolbarChapterArticles.addTopPadding(StatusBarUtils.getStatusBarHeight())
        toolbarChapterArticles.setNavigationOnClickListener {
            nav().navigateUp()
        }
        val fragments = ArrayList<Fragment>()
        chapter.children.forEach {
            fragments.add(
                ArticleListsFragment.newInstance(
                    ArticleType.TREE, it.id))
        }
        vpChapter.init(childFragmentManager, lifecycle, fragments)
            .postDelayed({ vpChapter.currentItem = index }, 50)
        TabLayoutMediator(tabChapter, vpChapter) { tab, position ->
            when (position) {
                position -> tab.text = chapter.children[position].name
            }
        }.attach()
    }

}