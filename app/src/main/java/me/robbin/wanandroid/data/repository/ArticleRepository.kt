package me.robbin.wanandroid.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import me.robbin.wanandroid.data.datasource.HomeDataSource
import me.robbin.wanandroid.data.datasource.ArticlesDataSource
import me.robbin.wanandroid.data.datasource.PublicArticleDataSource
import me.robbin.wanandroid.data.datasource.SearchArticleDateSource
import me.robbin.wanandroid.ui.fragment.common.ArticleType

/**
 *
 * Create by Robbin at 2020/7/11
 */

class ArticleRepository {

    companion object {
        val instance: ArticleRepository by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            ArticleRepository()
        }
    }

    fun getHomeArticles() =
        Pager(PagingConfig(pageSize = 20, enablePlaceholders = false)) {
            HomeDataSource()
        }.flow

    fun getPublicArticle(cid: Int) =
        Pager(PagingConfig(pageSize = 20, enablePlaceholders = false)) {
            PublicArticleDataSource(cid)
        }.flow

    fun getArticleList(type: ArticleType, cid: Int = -1) =
        Pager(PagingConfig(pageSize = 20, enablePlaceholders = false)) {
            ArticlesDataSource(type, cid)
        }.flow

    fun getSearchArticle(query: String) =
        Pager(PagingConfig(pageSize = 20, enablePlaceholders = false)) {
            SearchArticleDateSource(query)
        }.flow

}
