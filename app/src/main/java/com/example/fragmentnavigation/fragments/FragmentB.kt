package com.example.fragmentnavigation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.fragmentnavigation.R
import com.example.fragmentnavigation.contract.HasCustomTitle
import com.example.fragmentnavigation.contract.navigator
import com.example.fragmentnavigation.databinding.FragmentBBinding

class FragmentB : Fragment(), HasCustomTitle {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View = FragmentBBinding.inflate(inflater, container, false).apply {
        btnBack.setOnClickListener { onBackPressed() }
        btnGoC.setOnClickListener { onGoFragmentCPressed() }
    }.root

    override fun getTitleRes(): Int = R.string.fragment_b_title

    private fun onBackPressed() {
        navigator().goBack()
    }

    private fun onGoFragmentCPressed() {
        navigator().showFragmentC("Hello Fragment C")
    }
    companion object {
        const val FRAGMENT_B_TAG = "FRAGMENT_B_TAG"
    }
}