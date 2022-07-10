package com.boris.studentassistant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.boris.studentassistant.databinding.ActivityLogInBinding
import com.boris.studentassistant.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
    }
}