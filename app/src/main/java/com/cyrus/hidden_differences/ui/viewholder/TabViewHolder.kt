package com.cyrus.hidden_differences.ui.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.cyrus.hidden_differences.databinding.LayoutTabBinding

class TabViewHolder(
    private val binding: LayoutTabBinding,
    private val onClick: () -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind() {
        binding.root.setOnClickListener { onClick.invoke() }
    }
}