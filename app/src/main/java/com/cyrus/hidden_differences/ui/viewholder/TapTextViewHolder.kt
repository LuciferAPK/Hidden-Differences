package com.cyrus.hidden_differences.ui.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.cyrus.hidden_differences.databinding.LayoutTapTextBinding

class TapTextViewHolder(
    private val binding: LayoutTapTextBinding,
    private val onClick: () -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind() {
        binding.root.setOnClickListener { onClick.invoke() }
    }
}