package com.boris.studentassistant

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.boris.studentassistant.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.btRegister.setOnClickListener{
            requireActivity().run{
                startActivity(Intent(this, SignUpActivity::class.java))
                finish()
            }
        }
        binding.btLogIn.setOnClickListener {
            requireActivity().run{
                startActivity(Intent(this, LogInActivity::class.java))
                finish()
            }
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

