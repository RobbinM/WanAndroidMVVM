package me.robbin.wanandroid.ui.fragment.me.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import me.robbin.wanandroid.R
import me.robbin.wanandroid.model.LicensesBean
import me.robbin.wanandroid.databinding.RvItemLicensesBinding

/**
 * 开源项目列表适配器
 * Create by Robbin at 2020/7/27
 */
class LicensesAdapter :
    BaseQuickAdapter<LicensesBean, BaseDataBindingHolder<RvItemLicensesBinding>>(R.layout.rv_item_licenses) {

    override fun convert(holder: BaseDataBindingHolder<RvItemLicensesBinding>, item: LicensesBean) {
        val binding = holder.dataBinding
        if (binding != null) {
            binding.bean = item
        }
    }

}