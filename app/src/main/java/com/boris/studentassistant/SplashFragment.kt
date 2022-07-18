package com.boris.studentassistant

//import android.view.View
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.navigation.fragment.findNavController
import com.boris.studentassistant.databinding.FragmentSplashBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase


class SplashFragment : Fragment() {

    private var _binding: FragmentSplashBinding? = null
    private val binding get() = _binding!!

    private lateinit var sAuth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): RelativeLayout {

        Handler(Looper.getMainLooper()).postDelayed({
            sAuth = FirebaseAuth.getInstance()

            val user = sAuth.currentUser

            if (finishedOnBoarding()){
                if(user != null){
                    requireActivity().run{
                        startActivity(Intent(this, ChatActivity::class.java))
                        finish()}
                }else{
                    findNavController().navigate(R.id.action_splashFragment_to_homeFragment)
                }
            }
            else{
                findNavController().navigate(R.id.action_splashFragment_to_viewPagerFragment)
            }
        }, 3000)
        // Inflate the layout for this fragment
        _binding = FragmentSplashBinding.inflate(inflater, container, false)

        return binding.root
    }

    private fun finishedOnBoarding(): Boolean{

        val sharedPref = requireActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        return sharedPref.getBoolean("Finished", false)
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

