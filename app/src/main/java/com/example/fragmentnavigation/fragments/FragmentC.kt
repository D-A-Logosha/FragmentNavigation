package com.example.fragmentnavigation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.fragmentnavigation.R
import com.example.fragmentnavigation.contract.HasCustomTitle
import com.example.fragmentnavigation.contract.navigator
import com.example.fragmentnavigation.databinding.FragmentCBinding

class FragmentC : Fragment(), HasCustomTitle {

    private lateinit var string: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        string = savedInstanceState?.getString(KEY_STRING)
            ?: arguments?.getString(ARG_STRING)
                    ?: ""
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View = FragmentCBinding.inflate(inflater, container, false).apply {
        tvFargmentC.text = string
        btnBackA.setOnClickListener { onBackFragmentAPressed() }
        btnGoD.setOnClickListener { onGoFragmentDPressed() }
    }.root

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(KEY_STRING, string)
    }

    override fun getTitleRes(): Int = R.string.fragment_c_title

    private fun onBackFragmentAPressed() {
        navigator().backFragmentA()
    }

    private fun onGoFragmentDPressed() {
        navigator().showFragmentD()
    }

    companion object {
        const val FRAGMENT_C_TAG = "FRAGMENT_C_TAG"

        @JvmStatic
        private val ARG_STRING = "ARG_STRING"

        @JvmStatic
        private val KEY_STRING = "KEY_STRING"

        @JvmStatic
        fun newInstance(string: String): FragmentC {
            val args = Bundle()
            args.putString(ARG_STRING, string)
            val fragment = FragmentC()
            fragment.arguments = args
            return fragment
        }
    }
}