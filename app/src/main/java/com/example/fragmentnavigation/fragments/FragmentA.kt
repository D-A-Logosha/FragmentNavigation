package com.example.fragmentnavigation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.fragmentnavigation.R
import com.example.fragmentnavigation.contract.HasCustomTitle
import com.example.fragmentnavigation.contract.navigator
import com.example.fragmentnavigation.databinding.FragmentABinding

class FragmentA : Fragment(), HasCustomTitle {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View = FragmentABinding.inflate(inflater, container, false).apply {
        btnGoB.setOnClickListener { onGoFragmentBPressed() }
    }.root

    override fun getTitleRes(): Int = R.string.fragment_a_title

    private fun onGoFragmentBPressed() {
        navigator().showFragmentB()
    }
    companion object {
        const val FRAGMENT_A_TAG = "FRAGMENT_A_TAG"
        }
}