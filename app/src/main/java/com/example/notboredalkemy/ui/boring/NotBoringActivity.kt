package com.example.notboredalkemy.ui.boring

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.notboredalkemy.R
import com.example.notboredalkemy.data.model.Response
import com.example.notboredalkemy.databinding.ActivityNotBoringBinding
import com.example.notboredalkemy.utils.Utils
import org.koin.androidx.viewmodel.ext.android.viewModel

class NotBoringActivity : AppCompatActivity() {

    private val viewModel: NotBoringViewModel by viewModel()
    private var dataResponse: Response? = null
    private val type: String = Utils.category
    private val participants: Int = Utils.participants
    private lateinit var binding: ActivityNotBoringBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_not_boring)
        binding = ActivityNotBoringBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpObserver()
        viewModel.getActivities(type, participants)
    }

    private fun setUpObserver() {
        viewModel.dataResponseActivities.observe(this, { response ->
            when(response.first){
                true -> {
                    dataResponse = response.second as Response
                    fillActivityText(dataResponse)
                }
                false -> showErrorLayout()
            }
        })
    }

    private fun fillActivityText(dataResponse: Response?) {
        print(dataResponse?.accessibility)
        print(dataResponse?.key)
        print(dataResponse?.link)
        print(dataResponse?.participants)
    }

    private fun showErrorLayout() {
        val a = 0
    }

}