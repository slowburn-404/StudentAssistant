package com.boris.studentassistant.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.boris.studentassistant.onboarding.screens.FirstScreen
import com.boris.studentassistant.onboarding.screens.SecondScreen
import com.boris.studentassistant.onboarding.screens.ThirdScreen
import com.boris.studentassistant.databinding.FragmentViewPagerBinding
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator


class ViewPagerFragment : Fragment() {
    private var _binding: FragmentViewPagerBinding? = null
    private val binding get() = _binding!!
        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View {
            // Inflate the layout for this fragment
            _binding = FragmentViewPagerBinding.inflate(inflater, container, false)

            //add screens to this array
            val fragmentList = arrayListOf(
                FirstScreen(),
                SecondScreen(),
                ThirdScreen()
            )

            val adapter = ViewPagerAdapter(
                fragmentList,
                requireActivity().supportFragmentManager,
                lifecycle
            )

            val wormDotsIndicator: WormDotsIndicator = binding.wormDotsIndicator
            val viewPager2: ViewPager2 = binding.viewPager
            viewPager2.adapter = adapter
            wormDotsIndicator.attachTo(viewPager2)

            return binding.root

        }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        }
    }
