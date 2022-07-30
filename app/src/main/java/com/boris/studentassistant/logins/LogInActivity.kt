package com.boris.studentassistant.logins

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.boris.studentassistant.ChatActivity
import com.boris.studentassistant.databinding.ActivityLogInBinding
import com.google.firebase.auth.FirebaseAuth

class LogInActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLogInBinding

    private lateinit var sAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //hide actionbar
        supportActionBar?.hide()

        //hide progress bar
        binding.circularProgressBar.hide()

        sAuth = FirebaseAuth.getInstance()

        //password reset
        binding.textviewForgotPassword.setOnClickListener {
            val intent = Intent(this, PasswordResetActivity::class.java)
            startActivity(intent)
            finish()
        }

        //sign in user when button is tapped
        binding.btLogInPage.setOnClickListener {
            //fetch user entry
            val email = binding.textInputEditTextEmailLogIn.text.toString().trim()
            val passwd = binding.textInputEditTextPasswordLogIn.text.toString().trim()

            binding.circularProgressBar.show()

            studentLogIn(email, passwd)
        }
        binding.textSignUp.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            finish()
            startActivity(intent)
        }
    }
    private fun studentLogIn(email: String, passwd: String){
        //check for empty fields
        if (email.isNotEmpty() && passwd.isNotEmpty()) {
            sAuth.signInWithEmailAndPassword(email, passwd).addOnCompleteListener {
                if (it.isSuccessful) {
                    //open chat activity once auth is successful
                    val intent = Intent(this, ChatActivity::class.java)
                    finish()
                    startActivity(intent)
                } else {
                    Toast.makeText(this, it.exception.toString(), Toast.LENGTH_LONG).show()
                    binding.circularProgressBar.hide()
                }
            }
        } else {
            Toast.makeText(this, "Email and Password cannot be empty.", Toast.LENGTH_SHORT).show()
            binding.circularProgressBar.hide()
        }
    }

}