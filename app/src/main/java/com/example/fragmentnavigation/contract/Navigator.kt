package com.example.fragmentnavigation.contract

import androidx.fragment.app.Fragment

fun Fragment.navigator(): Navigator {
    return requireActivity() as Navigator
}

interface Navigator {

    fun showFragmentA()

    fun showFragmentB()

    fun showFragmentC(string: String)

    fun showFragmentD()

    fun backFragmentA()

    fun backFragmentB()

    fun goBack()
}