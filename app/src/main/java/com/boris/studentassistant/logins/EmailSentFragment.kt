package com.boris.studentassistant.logins

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.boris.studentassistant.databinding.FragmentEmailSentBinding


class EmailSentFragment : Fragment() {

    private var _binding: FragmentEmailSentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentEmailSentBinding.inflate(inflater, container, false)

        binding.btCancel.setOnClickListener{
            requireActivity().run{
                val intent = Intent(this, LogInActivity::class.java)
                startActivity(intent)
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