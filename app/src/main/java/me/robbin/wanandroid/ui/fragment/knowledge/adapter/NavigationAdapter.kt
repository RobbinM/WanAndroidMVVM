package me.robbin.wanandroid.ui.fragment.knowledge.adapter

import android.os.Bundle
import androidx.navigation.NavController
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import me.robbin.wanandroid.R
import me.robbin.wanandroid.model.Navigation
import me.robbin.wanandroid.databinding.RvItemKnowledgeBinding
import java.util.*

/**
 * 导航列表适配器
 * Create by Robbin at 2020/7/21
 */
class NavigationAdapter :
    BaseQuickAdapter<Navigation, BaseDataBindingHolder<RvItemKnowledgeBinding>>(R.layout.rv_item_knowledge) {

    private val chipItemCaches: Queue<Chip> = LinkedList()
    private var onItemChipClickListener: OnItemChipClickListener? = null

    override fun onViewRecycled(holder: BaseDataBindingHolder<RvItemKnowledgeBinding>) {
        val chipGroup = holder.getView<ChipGroup>(R.id.chipGroup)
        for (i in 0 until chipGroup.childCount) {
            chipItemCaches.offer(chipGroup.getChildAt(i) as Chip)
        }
        chipGroup.removeAllViews()
    }

    fun setOnItemChipClickListener(listener: OnItemChipClickListener) {
        this.onItemChipClickListener = listener
    }

    @Suppress("DEPRECATION")
    override fun convert(
        holder: BaseDataBindingHolder<RvItemKnowledgeBinding>,
        item: Navigation
    ) {
        val binding = holder.dataBinding
        if (binding != null) {
            binding.tvChapterName.text = item.name
            for (index in item.articles.indices) {
                val chip = createItemChip()
                chip.text = item.articles[index].title
                chip.isCheckable = false
                chip.isCloseIconVisible = false
                chip.setTextColor(context.resources.getColor(R.color.textSecondary))
                chip.setChipBackgroundColorResource(R.color.bgThird)
                chip.setOnClickListener {
                    val bundle = Bundle()
                    val bean = item.articles[index]
                    bundle.putString("url", bean.link)
                    bundle.putString("title", bean.title)
                    bundle.putInt("articleId", bean.id)
                    bundle.putBoolean("collected", bean.collect)
                    bundle.putString("author", bean.author)
                    bundle.putInt("userId", bean.userId)
                    onItemChipClickListener?.setNavController()
                        ?.navigate(R.id.action_global_to_webFragment, bundle)
                }
                binding.chipGroup.addView(chip)
            }
        }
    }

    private fun createItemChip(): Chip {
        val chip = chipItemCaches.poll()
        if (chip != null) {
            return chip
        }
        return Chip(context)
    }

    interface OnItemChipClickListener {
        fun setNavController(): NavController
    }

}