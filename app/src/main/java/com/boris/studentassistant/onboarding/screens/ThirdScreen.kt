package com.boris.studentassistant.onboarding.screens

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
/*import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2*/
import com.boris.studentassistant.R
import com.boris.studentassistant.databinding.FragmentThirdScreenBinding

class ThirdScreen : Fragment() {

    private var _binding: FragmentThirdScreenBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentThirdScreenBinding.inflate(inflater, container, false)


        binding.finish.setOnClickListener {
            findNavController().navigate(R.id.action_viewPagerFragment_to_homeFragment)
            finishOnBoarding()
        }
        return binding.root
    }

    private fun finishOnBoarding(){
        val sharedPref = requireActivity().getSharedPreferences( "onBoarding", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putBoolean("Finished", true)
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}