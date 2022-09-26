package com.boris.studentassistant.studentDashboard

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.WindowCompat
import com.boris.studentassistant.ChatActivity
import com.boris.studentassistant.CoursesActivity
import com.boris.studentassistant.R
import com.boris.studentassistant.databinding.ActivityDashboardBinding
import com.boris.studentassistant.logins.LogInActivity
import com.google.firebase.auth.FirebaseAuth

class DashboardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDashboardBinding
    //firebase
    private lateinit var sAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //set app to fullscreen
        WindowCompat.setDecorFitsSystemWindows(window, false)

        //set toolbar title
        binding.dashboardToolbar.setTitle(R.string.app_name)

        //initialise firebase
        sAuth = FirebaseAuth.getInstance()

        //navigation
        binding.dashboardToolbar.setNavigationOnClickListener {
            binding.dashboardDrawerLayout.open()
        }
        binding.dashboardNavigationView.setNavigationItemSelectedListener { menuItem ->
            menuItem.isChecked = true
            //handle item selected
            when(menuItem.itemId){
                R.id.assistant -> {
                    val intent = Intent(this, ChatActivity::class.java)
                    startActivity(intent)
                }
                R.id.courses -> {
                    val intent = Intent(this, CoursesActivity::class.java)
                    startActivity(intent)
                }
                R.id.log_out -> {
                    sAuth.signOut()
                    val intent = Intent(this, LogInActivity::class.java)
                    finishAffinity()
                    startActivity(intent)
                }
               // R.id.developer_email -> {

               // }
            }
            binding.dashboardDrawerLayout.close()
            true
        }


        binding.dashboardFloatingactionbutton.setOnClickListener {
            val intent = Intent(this, ChatActivity::class.java)
            startActivity(intent)
        }
    }
}