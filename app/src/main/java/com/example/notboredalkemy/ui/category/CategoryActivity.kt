package com.example.notboredalkemy.ui.category

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.notboredalkemy.R
import com.example.notboredalkemy.databinding.ActivityCategoryBinding
import com.example.notboredalkemy.databinding.ActivityHomeBinding
import com.example.notboredalkemy.databinding.ToolbarBaseBinding
import com.example.notboredalkemy.ui.adapter.BoringAdapter

class CategoryActivity : AppCompatActivity() {

    private var recyclerView : RecyclerView? = null
    private var adapter: BoringAdapter? = null
    private lateinit var binding: ActivityCategoryBinding
    private var categoryList: List<String> = listOf()
    private lateinit var bindingToolbar: ToolbarBaseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryBinding.inflate(layoutInflater)
        bindingToolbar = ToolbarBaseBinding.bind(binding.root)
        bindingToolbar.tvToolbarTitle.text = getString(R.string.activitiesUI)
        bindingToolbar.btnRandom.isVisible = true

        setContentView(binding.root)
        setUpOnClickListener()
        setUpRecyclerView()
        setUpObserver()
    }

    private fun setUpRecyclerView() {
        recyclerView = binding.rvActivities
        adapter = BoringAdapter(categoryList)
        recyclerView?.apply {
            layoutManager = LinearLayoutManager(context)
            isNestedScrollingEnabled = false
            adapter = adapter
        }
    }

    private fun setUpOnClickListener() {
        val a = 0
    }

    private fun setUpObserver() {
        val a = 0
    }
}