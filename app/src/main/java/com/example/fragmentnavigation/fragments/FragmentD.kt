package com.example.fragmentnavigation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.fragmentnavigation.R
import com.example.fragmentnavigation.contract.HasCustomTitle
import com.example.fragmentnavigation.contract.navigator
import com.example.fragmentnavigation.databinding.FragmentDBinding

class FragmentD : Fragment(), HasCustomTitle {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View = FragmentDBinding.inflate(inflater, container, false).apply {
        btnBackB.setOnClickListener { onBackFragmentBPressed() }
    }.root

    override fun getTitleRes(): Int = R.string.fragment_d_title

    private fun onBackFragmentBPressed() {
        navigator().backFragmentB()
    }
    companion object {
        const val FRAGMENT_D_TAG = "FRAGMENT_D_TAG"
    }
}