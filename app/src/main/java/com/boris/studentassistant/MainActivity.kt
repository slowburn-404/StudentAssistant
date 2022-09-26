package com.boris.studentassistant


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.WindowCompat
import com.boris.studentassistant.databinding.ActivityMainBinding
import com.boris.studentassistant.databinding.FragmentHomeBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //set app to fullscreen
        WindowCompat.setDecorFitsSystemWindows(window, false)

        supportActionBar?.hide()


    }
}