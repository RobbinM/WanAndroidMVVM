package me.robbin.wanandroid.ui.fragment.knowledge.viewmodel

import androidx.lifecycle.MutableLiveData
import me.robbin.wanandroid.app.base.BaseVM
import me.robbin.wanandroid.api.ApiService
import me.robbin.wanandroid.model.Chapter
import me.robbin.wanandroid.model.Navigation

/**
 * 模块 ViewModel
 * Create by Robbin at 2020/7/15
 */
class ChapterListViewModel : BaseVM() {

    val knowledgeList: MutableLiveData<MutableList<Chapter>> = MutableLiveData()
    val navigationList: MutableLiveData<MutableList<Navigation>> = MutableLiveData()
    val publicList: MutableLiveData<MutableList<Chapter>> = MutableLiveData()

    /**
     * 获取体系列表
     * Create by Robbin at 2020/7/15
     */
    fun getKnowledgeList() {
        launchGo(
            { knowledgeList.value = ApiService.getApi().getKnowledgeList().data }
        )
    }

    /**
     * 获取导航列表
     * Create by Robbin at 2020/7/15
     */
    fun getNavigationList() {
        launchGo(
            { navigationList.value = ApiService.getApi().getNaviList().data }
        )
    }

    /**
     * 获取公众号列表
     * Create by Robbin at 2020/7/15
     */
    fun getPublicList() {
        launchGo(
            { publicList.value = ApiService.getApi().getPublicList().data }
        )
    }

}