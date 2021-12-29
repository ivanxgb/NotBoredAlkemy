package com.example.notboredalkemy.ui.category

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.notboredalkemy.R
import com.example.notboredalkemy.databinding.ActivityCategoryBinding
import com.example.notboredalkemy.databinding.ActivityHomeBinding
import com.example.notboredalkemy.ui.adapter.BoringAdapter

class CategoryActivity : AppCompatActivity() {

    private var recyclerView : RecyclerView? = null
    private var adapter: BoringAdapter? = null
    private lateinit var binding: ActivityCategoryBinding
    private var categoryList: List<String> = listOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryBinding.inflate(layoutInflater)
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
        TODO("Not yet implemented")
    }

    private fun setUpObserver() {
        TODO("Not yet implemented")
    }
}