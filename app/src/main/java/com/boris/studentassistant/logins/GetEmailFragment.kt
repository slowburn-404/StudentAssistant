package com.boris.studentassistant.logins

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.boris.studentassistant.R
import com.boris.studentassistant.databinding.FragmentGetEmailBinding
import com.google.firebase.auth.FirebaseAuth

class GetEmailFragment : Fragment() {

    private var _binding: FragmentGetEmailBinding? = null
    private val binding get() = _binding!!

    private lateinit var sAuth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentGetEmailBinding.inflate(inflater, container, false)

        //hide progress bar
        binding.circularProgressBar.hide()

        //initialise firebase
        sAuth = FirebaseAuth.getInstance()

        binding.submitEmail.setOnClickListener {
            val email = binding.textInputEditTextGetEmail.text.toString().trim()
            if(email.isNotEmpty() && sAuth.currentUser == null){
                binding.circularProgressBar.show()

                sAuth.sendPasswordResetEmail(email).addOnCompleteListener {
                    if(it.isSuccessful){
                        findNavController().navigate(R.id.action_getEmailFragment_to_emailSentFragment2)
                    }else{
                        binding.circularProgressBar.hide()
                        Toast.makeText(requireContext(), it.exception?.message.toString(), Toast.LENGTH_LONG).show()
                    }
                }
            }else{
                binding.circularProgressBar.hide()
                Toast.makeText(requireContext(),"Please enter Email Address.", Toast.LENGTH_SHORT).show()
            }
        }

        return binding.root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}