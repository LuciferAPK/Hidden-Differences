package com.cyrus.hidden_differences.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cyrus.hidden_differences.data.model.GameType
import com.cyrus.hidden_differences.databinding.LayoutTabBinding
import com.cyrus.hidden_differences.databinding.LayoutTapFruitBinding
import com.cyrus.hidden_differences.databinding.LayoutTapImageBinding
import com.cyrus.hidden_differences.databinding.LayoutTapNumberBinding
import com.cyrus.hidden_differences.databinding.LayoutTapTextBinding
import com.cyrus.hidden_differences.databinding.LayoutTapUltraHardBinding
import com.cyrus.hidden_differences.enums.GameViewType
import com.cyrus.hidden_differences.ext.viewBinding
import com.cyrus.hidden_differences.ui.viewholder.TabViewHolder
import com.cyrus.hidden_differences.ui.viewholder.TapFruitViewHolder
import com.cyrus.hidden_differences.ui.viewholder.TapImageViewHolder
import com.cyrus.hidden_differences.ui.viewholder.TapNumberViewHolder
import com.cyrus.hidden_differences.ui.viewholder.TapTextViewHolder
import com.cyrus.hidden_differences.ui.viewholder.TapUltraHardViewHolder
import java.util.concurrent.Executors

class GameAdapter(
    private val onClickItem: () -> Unit
) : ListAdapter<Any, RecyclerView.ViewHolder>(
    AsyncDifferConfig.Builder(ItemCallback())
        .setBackgroundThreadExecutor(Executors.newSingleThreadExecutor())
        .build()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            GameViewType.TAB_TYPE.viewType -> {
                val binding = parent.viewBinding(LayoutTabBinding::inflate)
                TabViewHolder(binding, onClickItem)
            }

            GameViewType.TAP_NUMBER_TYPE.viewType -> {
                val binding = parent.viewBinding(LayoutTapNumberBinding::inflate)
                TapNumberViewHolder(binding, onClickItem)
            }

            GameViewType.TAP_TEXT_TYPE.viewType -> {
                val binding = parent.viewBinding(LayoutTapTextBinding::inflate)
                TapTextViewHolder(binding, onClickItem)
            }

            GameViewType.TAP_IMAGE_TYPE.viewType -> {
                val binding = parent.viewBinding(LayoutTapImageBinding::inflate)
                TapImageViewHolder(binding, onClickItem)
            }

            GameViewType.TAP_FRUIT_TYPE.viewType -> {
                val binding = parent.viewBinding(LayoutTapFruitBinding::inflate)
                TapFruitViewHolder(binding, onClickItem)
            }

            else -> {
                val binding = parent.viewBinding(LayoutTapUltraHardBinding::inflate)
                TapUltraHardViewHolder(binding, onClickItem)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is TabViewHolder -> holder.bind()
            is TapNumberViewHolder -> holder.bind()
            is TapTextViewHolder -> holder.bind()
            is TapImageViewHolder -> holder.bind()
            is TapFruitViewHolder -> holder.bind()
            is TapUltraHardViewHolder -> holder.bind()
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (val item = getItem(position)) {
            is GameType -> {
                when (item.gameType) {
                    GameViewType.TAB_TYPE.nameGame -> GameViewType.TAB_TYPE.viewType
                    GameViewType.TAP_NUMBER_TYPE.nameGame -> GameViewType.TAP_NUMBER_TYPE.viewType
                    GameViewType.TAP_TEXT_TYPE.nameGame -> GameViewType.TAP_TEXT_TYPE.viewType
                    GameViewType.TAP_IMAGE_TYPE.nameGame -> GameViewType.TAP_IMAGE_TYPE.viewType
                    GameViewType.TAP_FRUIT_TYPE.nameGame -> GameViewType.TAP_FRUIT_TYPE.viewType
                    else -> GameViewType.TAP_ULTRA_HARD_TYPE.viewType
                }
            }
            else -> GameViewType.TAP_ULTRA_HARD_TYPE.viewType
        }
    }

    override fun submitList(list: List<Any>?) {
        super.submitList(ArrayList<Any>(list ?: listOf()))
    }

    class ItemCallback : DiffUtil.ItemCallback<Any>() {
        override fun areItemsTheSame(oldItem: Any, newItem: Any): Boolean {
            return if (oldItem is GameType && newItem is GameType) oldItem.type == newItem.type
            else false
        }

        override fun areContentsTheSame(oldItem: Any, newItem: Any): Boolean {
            return if (oldItem is GameType && newItem is GameType) oldItem.type == newItem.type
            else false
        }
    }
}