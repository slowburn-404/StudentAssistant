package com.boris.studentassistant.onboarding.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
//import androidx.viewpager2.widget.ViewPager2
//import androidx.fragment.app.FragmentTransaction
//import com.boris.studentassistant.R
import com.boris.studentassistant.databinding.FragmentSecondScreenBinding
//import com.boris.studentassistant.databinding.FragmentViewPagerBinding


class SecondScreen : Fragment() {

    private var _binding: FragmentSecondScreenBinding? = null
    private val binding get() = _binding!!

    //private var _bindingviewPager: FragmentViewPagerBinding? = null
    //private val bindingviewPager get() = _bindingviewPager!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
       _binding = FragmentSecondScreenBinding.inflate(inflater, container, false)

        //referencing viewpager
        //val viewPager = binding.root.rootView.findViewById<ViewPager2>(R.id.viewPager)

        //binding.next2.setOnClickListener {
          //  viewPager?.currentItem = 2
        //}
        return binding.root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
       // _bindingviewPager = null
    }
}


