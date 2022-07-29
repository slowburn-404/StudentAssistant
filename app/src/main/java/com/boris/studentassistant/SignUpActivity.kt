package com.boris.studentassistant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import com.boris.studentassistant.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.boris.studentassistant.models.User

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding

    private lateinit var sAuth: FirebaseAuth

    private lateinit var sADBRef: DatabaseReference

    override fun onResume() {
        super.onResume()
            //dropdown
            val roles = resources.getStringArray(R.array.roles)
            val arrayAdapter = ArrayAdapter(this, R.layout.roles_dropdown, roles)
            binding.autoCompleteTextViewRole.setAdapter(arrayAdapter)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        //initialise firebase
        sAuth = FirebaseAuth.getInstance()

        //perform signup on button tap
        binding.btSignUp.setOnClickListener {
            //fetch user input
            val email = binding.textInputEditTextEmail.text.toString().trim()
            val passwd = binding.textInputEditTextPassword.text.toString().trim()
            val confirmpasswd = binding.textInputEditTextConfirmPassword.text.toString().trim()
            val username = binding.textInputEditTextUsername.text.toString().trim()

            userSignUP(email, passwd, confirmpasswd, username)
        }
        binding.textLogIn.setOnClickListener {
            val intent = Intent(this, LogInActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
    private fun userSignUP(email: String, passwd: String, confirmpasswd: String, username: String){
        //check if fields are empty
        if(email.isNotEmpty() && passwd.isNotEmpty() && confirmpasswd.isNotEmpty()){
            //check if passwords match
            if(passwd == confirmpasswd){
                sAuth.createUserWithEmailAndPassword(email, passwd).addOnCompleteListener {
                    if (it.isSuccessful){
                        //add user to database
                        addUserToDB(email, sAuth.currentUser?.uid!!, username)

                        Toast.makeText(this, "Account created successfully", Toast.LENGTH_SHORT).show()
                        //move to log in activity if signup is successful
                        val intent = Intent(this, LogInActivity::class.java)
                        startActivity(intent)
                        finish()
                    }else{
                        Toast.makeText(this, it.exception.toString(), Toast.LENGTH_LONG).show()
                    }
                }
            } else{
                Toast.makeText(this, "Passwords do not match.", Toast.LENGTH_SHORT).show()
            }

        } else{
            Toast.makeText(this, "Email and Password cannot be empty.", Toast.LENGTH_SHORT).show()
        }

    }

    private fun addUserToDB(email: String, uid: String, username: String){
        sADBRef = FirebaseDatabase.getInstance().reference

        val role = binding.autoCompleteTextViewRole.text.toString()

        if(role == "Student"){
            sADBRef.child("Students").child(uid).setValue(User(email,uid,username))
        }else{
            sADBRef.child("Lecturers").child(uid).setValue(User(email,uid,username))
        }
    }
}