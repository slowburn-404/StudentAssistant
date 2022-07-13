package com.boris.studentassistant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.boris.studentassistant.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding

    private lateinit var sAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        sAuth = FirebaseAuth.getInstance()

        binding.btSignUp.setOnClickListener {
            val email = binding.textInputEditTextEmail.text.toString()
            val passwd = binding.textInputEditTextPassword.text.toString()
            val confirmpasswd = binding.textInputEditTextConfirmPassword.text.toString()

            if(email.isNotEmpty() && passwd.isNotEmpty() && confirmpasswd.isNotEmpty()){
                if(passwd == confirmpasswd){
                    sAuth.createUserWithEmailAndPassword(email, passwd).addOnCompleteListener {
                        if (it.isSuccessful){
                            Toast.makeText(this, "Account created successfully", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this, LogInActivity::class.java)
                            startActivity(intent)

                        }else{
                            Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                        }
                    }
                } else{
                    Toast.makeText(this, "Passwords do not match.", Toast.LENGTH_SHORT).show()
                }

            } else{
                Toast.makeText(this, "Email and Password cannot be empty.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}