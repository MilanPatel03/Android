package com.example.quickecommerce.auth

import android.app.Fragment
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.quickecommerce.R
import com.example.quickecommerce.databinding.FragmentSplashBinding

class SplashFragment : Fragment() {

    private lateinit var binding: FragmentSplashBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout first
        binding = FragmentSplashBinding.inflate(inflater, container, false)

        // Add padding to avoid content overlap with status bar
        binding.root.setPadding(0, getStatusBarHeight(), 0, 0)

//      setStatusBarTransparent()

        //binding = FragmentSplashBinding.inflate(layoutInflater)

        Handler(Looper.getMainLooper()).postDelayed(
            { findNavController().navigate(R.id.action_splashFragment_to_signInFragment) },
            3000
        )

        return binding.root
    }


    private fun getStatusBarHeight(): Int {
        val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
        return if (resourceId > 0) resources.getDimensionPixelSize(resourceId) else 0
    }

    override fun onDestroyView() {
        super.onDestroyView()
        false
    }

}