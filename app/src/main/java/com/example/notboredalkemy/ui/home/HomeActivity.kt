package com.example.notboredalkemy.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.CheckBox
import androidx.core.view.isVisible
import com.example.notboredalkemy.databinding.ActivityHomeBinding
import com.example.notboredalkemy.ui.category.CategoryActivity
import com.example.notboredalkemy.ui.terms.TermsActivity
import com.example.notboredalkemy.utils.Constants
import com.example.notboredalkemy.utils.Utils
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : AppCompatActivity() {

    private var participants: String = Constants.EMPTY
    private val viewModel: HomeViewModel by viewModel()
    private var participantsValid: Boolean = true
    private var isCheckboxChecked: Boolean = false
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableButton()
        setUpOnClickListener()
        onChangeParticipants()
        onChangeRangePrice()
        onClickListenerCheckbox()
        setUpObserver()
    }

    private fun onChangeRangePrice() {
        binding.slider.addOnChangeListener { slider, value, fromUser ->
            val values = binding.slider.values
            Utils.minPrice = values[0].toDouble()
            Utils.maxPrice = values[1].toDouble()

        }
    }

    private fun onClickListenerCheckbox() {
        val checkBox: CheckBox = binding.checkBox
        val checkboxPrice: CheckBox = binding.chPrice
        checkBox.setOnCheckedChangeListener { _, isChecked ->
            isCheckboxChecked = isChecked
            enableButton()
        }
        checkboxPrice.setOnCheckedChangeListener { _, isChecked ->
            binding.slider.isVisible = isChecked
            Utils.isPriceSelected = isChecked
        }
    }

    private fun onChangeParticipants() {
        binding.etParticipants.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                captureParticipants()
            }
        })
    }

    private fun setUpObserver() {
        viewModel.dataResponseValidate.observe(this, { response ->
            participantsValid = response
            enableButton()
        })
    }

    private fun enableButton() {
        binding.btnStart.isEnabled =isCheckboxChecked && participantsValid
    }

    private fun captureParticipants() {
        participants = binding.etParticipants.text.toString()
        viewModel.validateParticipants(participants)
    }

    private fun setUpOnClickListener() {
        binding.tvTerms.setOnClickListener{
            startActivity(Intent(this, TermsActivity::class.java))
        }
        binding.btnStart.setOnClickListener {
        if (binding.btnStart.isEnabled) {
            Utils.participants = participants.toInt()
            startActivity(Intent(this, CategoryActivity::class.java))
            }
        }
    }
}