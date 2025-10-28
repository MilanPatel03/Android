package com.example.quickecommerce.auth

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.quickecommerce.R
import com.example.quickecommerce.databinding.FragmentOTPBinding

class OTPFragment : Fragment() {


    private lateinit var binding: FragmentOTPBinding
    private lateinit var userNumber: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOTPBinding.inflate(layoutInflater)

        getUserNumber()
        return binding.root
    }

    private fun getUserNumber() {
        val bundle = arguments
        userNumber = bundle?.getString("number").toString()

        binding.userNumber.text = userNumber
    }

}