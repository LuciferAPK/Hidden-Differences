package com.cyrus.hidden_differences.ui.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.cyrus.hidden_differences.databinding.LayoutTapImageBinding

class TapImageViewHolder(
    private val binding: LayoutTapImageBinding,
    private val onClick: () -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind() {
        binding.root.setOnClickListener { onClick.invoke() }
    }
}