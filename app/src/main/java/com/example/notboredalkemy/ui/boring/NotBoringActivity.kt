package com.example.notboredalkemy.ui.boring

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import com.example.notboredalkemy.R
import com.example.notboredalkemy.data.model.Response
import com.example.notboredalkemy.databinding.ActivityNotBoringBinding
import com.example.notboredalkemy.databinding.ToolbarBaseBinding
import com.example.notboredalkemy.utils.Constants
import com.example.notboredalkemy.utils.Utils
import org.koin.androidx.viewmodel.ext.android.viewModel

class NotBoringActivity : AppCompatActivity() {

    private val viewModel: NotBoringViewModel by viewModel()
    private var dataResponse: Response? = null
    private var categorySelected: Boolean = Utils.isCategorySelected
    private val type: String = Utils.category
    private val participants: Int = Utils.participants
    private var money: String = Constants.EMPTY
    private val low = Utils.low
    private val medium = Utils.medium
    private  val high = Utils.high
    private lateinit var binding: ActivityNotBoringBinding
    private lateinit var bindingToolbar: ToolbarBaseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_not_boring)
        binding = ActivityNotBoringBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpTextInToolbar()
        setUpObserver()
        setUpOnClickListener()
        validateCategorySelected(categorySelected)
    }

    private fun validateCategorySelected(categorySelected: Boolean) {
        if(categorySelected){
            viewModel.getActivities(type, participants)
        }else{
            viewModel.getRandomActivity()
        }
    }

    private fun setUpOnClickListener() {
        binding.btnTryAnother.setOnClickListener { viewModel.getActivities(type, participants) }
    }

    private fun setUpTextInToolbar() {
        bindingToolbar = ToolbarBaseBinding.bind(binding.root)
        bindingToolbar.tvToolbarTitle.text = Utils.category
        bindingToolbar.btnBack.isVisible = true
        bindingToolbar.btnBack.setOnClickListener { finish() }
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
        viewModel.dataResponseRandomActivity.observe(this, { response ->
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
        binding.tvMoneyAmount.text = setUpPriceRange(dataResponse?.price)
        binding.tvActivityName.text = dataResponse?.activity
        binding.tvParticipantsNumber.text = dataResponse?.participants.toString()
        binding.tvCategory.text = dataResponse?.type
    }

    private fun setUpPriceRange(price: Double?): String {
        price?.run {
            money = when (price) {
                in low -> Constants.LOW
                in medium -> Constants.MEDIUM
                in high -> Constants.HIGH
                else -> Constants.FREE
            }
        }
        return money
    }

    private fun showErrorLayout() {
        val a = 0
    }
}