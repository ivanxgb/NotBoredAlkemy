package com.example.notboredalkemy.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.notboredalkemy.databinding.CategoryItemBinding

class BoringAdapter(
        private val categories: List<String>,
        private var listenerCategory: (String) -> Unit
): RecyclerView.Adapter<BoringAdapter.ViewHolder>() {

    private lateinit var mContext: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        mContext = parent.context
        val binding = CategoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = categories.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(categories[position], listenerCategory)
    }


    class ViewHolder( private val binding: CategoryItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindData(category: String, listenerCategory: (String) -> Unit) {
            binding.tvCategoryName.text = category
            binding.clCategory.setOnClickListener { listenerCategory.invoke(category) }
        }
    }
}