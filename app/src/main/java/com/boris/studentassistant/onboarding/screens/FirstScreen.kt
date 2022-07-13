package com.boris.studentassistant.onboarding.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.boris.studentassistant.R
//import androidx.fragment.app.FragmentTransaction
//import androidx.navigation.fragment.findNavController
//import com.boris.studentassistant.R
// import androidx.fragment.app.activityViewModels
import com.boris.studentassistant.databinding.FragmentFirstScreenBinding
//import com.boris.studentassistant.databinding.FragmentViewPagerBinding


class FirstScreen : Fragment() {

    private var _binding: FragmentFirstScreenBinding? = null
    private val binding get() = _binding!!

    //private var _bindingviewPager: FragmentViewPagerBinding? = null
    //private val bindingviewPager get() = _bindingviewPager!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentFirstScreenBinding.inflate(inflater, container, false)

        //referencing viewpager
       //_bindingviewPager = FragmentViewPagerBinding.inflate(inflater, container, false)

        val viewPager = binding.root.rootView.findViewById<ViewPager2>(R.id.viewPager)

                     binding.next1.setOnClickListener {
                     viewPager?.currentItem = 1

                 }
        return binding.root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        //_bindingviewPager = null
    }
}