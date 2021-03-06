package com.boris.studentassistant.studentDashboard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.boris.studentassistant.R
import com.boris.studentassistant.databinding.ActivityDashboardBinding

class DashboardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //
        binding.dashboardToolbar.setTitle(R.string.app_name)
    }
}