package com.example.notboredalkemy.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import com.example.notboredalkemy.databinding.ActivityHomeBinding
import com.example.notboredalkemy.ui.category.CategoryActivity
import com.example.notboredalkemy.ui.terms.TermsActivity
import com.example.notboredalkemy.utils.Constants
import com.example.notboredalkemy.utils.Utils
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : AppCompatActivity() {

    private var participants: String = Constants.EMPTY
    private val viewModel: HomeViewModel by viewModel()
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpOnClickListener()
        onChangeParticipants()
        setUpObserver()
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
        viewModel.dataResponseValid.observe(this, { response ->
            binding.btnStart.isEnabled = response.first
        })
    }

    private fun captureParticipants() {
        participants = binding.etParticipants.text.toString()
        viewModel.validateParticipants(participants)
        Utils.participants = participants.toInt()
    }

    private fun setUpOnClickListener() {
        binding.tvTerms.setOnClickListener{
            startActivity(Intent(this, TermsActivity::class.java))
        }
        binding.btnStart.setOnClickListener {
        if (binding.btnStart.isEnabled) {
            startActivity(Intent(this, CategoryActivity::class.java))
            }
        }
    }
}