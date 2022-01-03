package com.example.notboredalkemy.ui.category

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.notboredalkemy.R
import com.example.notboredalkemy.databinding.ActivityCategoryBinding
import com.example.notboredalkemy.databinding.ToolbarBaseBinding
import com.example.notboredalkemy.ui.adapter.BoringAdapter
import com.example.notboredalkemy.ui.boring.NotBoringActivity
import com.example.notboredalkemy.utils.Utils
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel

class CategoryActivity : AppCompatActivity() {

    private var recyclerView: RecyclerView? = null
    private var mAdapter: BoringAdapter? = null
    private val viewModel: CategoryViewModel by viewModel()
    private val listOfCategories = mutableListOf<String>()
    private lateinit var binding: ActivityCategoryBinding
    private lateinit var bindingToolbar: ToolbarBaseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpTextInToolbar()
        setUpRecyclerView()
        setUpObserver()
        viewModel.getCategories()
    }

    private fun setUpTextInToolbar() {
        bindingToolbar = ToolbarBaseBinding.bind(binding.root)
        bindingToolbar.tvToolbarTitle.text = getString(R.string.activitiesUI)
        bindingToolbar.btnRandom.isVisible = true
        bindingToolbar.btnBack.isVisible = true
        bindingToolbar.btnBack.setOnClickListener { finish() }
        bindingToolbar.btnRandom.setOnClickListener { viewModel.getRandomCategory() }
    }

    private fun setUpRecyclerView() {
        recyclerView = binding.rvActivities
        mAdapter = BoringAdapter(listOfCategories, ::callbackCategory)
        recyclerView?.apply {
            layoutManager = LinearLayoutManager(context)
            isNestedScrollingEnabled = false
            adapter = mAdapter
        }
    }

    private fun callbackCategory(category: String) {
        Utils.category = category
        Utils.isCategorySelected = true
        startActivity(Intent(this, NotBoringActivity::class.java))
    }

    private fun setUpObserver() {
        viewModel.dataResponseCategories.observe(this, { response ->
            when (response.first) {
                true -> listOfCategories.addAll(response.second as MutableList<String>)
                false -> noCategoriesAvailable()
            }
        })
        viewModel.dataResponseRandom.observe(this, { response ->
            when(response.first){
            true -> startActivity(Intent(this, NotBoringActivity::class.java))
            false ->  Snackbar.make(binding.root, getString(R.string.app_name), Snackbar.LENGTH_SHORT).show()
            }
        })
    }

    private fun noCategoriesAvailable() {
        val a = 0
    }
}