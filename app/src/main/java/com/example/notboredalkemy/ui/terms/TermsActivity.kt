package com.example.notboredalkemy.ui.terms

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.notboredalkemy.R
import com.example.notboredalkemy.databinding.ActivityHomeBinding
import com.example.notboredalkemy.databinding.ActivityTermsBinding

class TermsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTermsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTermsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpOnClickListener()
    }

    private fun setUpOnClickListener() {
        binding.btnClose.setOnClickListener{
            finish()
        }
    }
}