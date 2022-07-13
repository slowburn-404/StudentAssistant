package com.boris.studentassistant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.boris.studentassistant.databinding.ActivityLogInBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase

class LogInActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLogInBinding

    private lateinit var sAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        sAuth = FirebaseAuth.getInstance()

        binding.btLogInPage.setOnClickListener {

            val email = binding.textInputEditTextEmailLogIn.text.toString()
            val passwd = binding.textInputEditTextPasswordLogIn.text.toString()

            if (email.isNotEmpty() && passwd.isNotEmpty()) {
                sAuth.signInWithEmailAndPassword(email, passwd).addOnCompleteListener {
                    if (it.isSuccessful) {
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                Toast.makeText(this, "Email and Password cannot be empty.", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

}