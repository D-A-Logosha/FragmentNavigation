package com.example.fragmentnavigation

import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import com.example.fragmentnavigation.contract.HasCustomTitle
import com.example.fragmentnavigation.contract.Navigator
import com.example.fragmentnavigation.databinding.ActivityMainBinding
import com.example.fragmentnavigation.fragments.FragmentA
import com.example.fragmentnavigation.fragments.FragmentB
import com.example.fragmentnavigation.fragments.FragmentC
import com.example.fragmentnavigation.fragments.FragmentD

class MainActivity : AppCompatActivity(), Navigator {

    private lateinit var binding: ActivityMainBinding

    private lateinit var homeFragmentTag :String

    private val currentFragment: Fragment
        get() = supportFragmentManager.findFragmentById(R.id.fragmentContainer)!!

    private val fragmentListener = object : FragmentManager.FragmentLifecycleCallbacks() {
        override fun onFragmentViewCreated(
            fm: FragmentManager,
            f: Fragment,
            v: View,
            savedInstanceState: Bundle?,
        ) {
            super.onFragmentViewCreated(fm, f, v, savedInstanceState)
            updateUi()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also { setContentView(it.root) }
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        homeFragmentTag = FragmentA.FRAGMENT_A_TAG
        /*if (savedInstanceState == null) {
            if (supportFragmentManager.findFragmentByTag(FragmentA.FRAGMENT_A_TAG) == null) {
                val fragment = FragmentA()
                supportFragmentManager.commit {
                    setCustomAnimations(
                        R.anim.slide_in,
                        R.anim.fade_out,
                        R.anim.fade_in,
                        R.anim.slide_out
                    )
                    replace(R.id.fragmentContainer, fragment, FragmentA.FRAGMENT_A_TAG)
                    //addToBackStack(FragmentA.FRAGMENT_A_TAG)
                }
                supportFragmentManager.popBackStack()
            }
        }*/
        supportFragmentManager.registerFragmentLifecycleCallbacks(fragmentListener, false)
    }

    override fun onDestroy() {
        super.onDestroy()
        supportFragmentManager.unregisterFragmentLifecycleCallbacks(fragmentListener)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        updateUi()
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }

    override fun showFragmentA() {
        launchFragment(FragmentA())
    }

    override fun showFragmentB() {
        launchFragment(FragmentB())
    }

    override fun showFragmentC(string: String) {
        launchFragment(FragmentC.newInstance(string))
    }

    override fun showFragmentD() {
        launchFragment(FragmentD())
    }

    override fun backFragmentA() {
        backFragment(FragmentA.FRAGMENT_A_TAG)
    }

    override fun backFragmentB() {
        backFragment(FragmentB.FRAGMENT_B_TAG)
    }


    override fun goBack() {
        onBackPressedDispatcher.onBackPressed()
    }

    private fun launchFragment(fragment: Fragment) {
        val tag: String = when (fragment) {
            is FragmentA -> FragmentA.FRAGMENT_A_TAG
            is FragmentB -> FragmentB.FRAGMENT_B_TAG
            is FragmentC -> FragmentC.FRAGMENT_C_TAG
            is FragmentD -> FragmentD.FRAGMENT_D_TAG
            else -> "UNKNOWN"
        }
        supportFragmentManager.commit {
            setCustomAnimations(
                R.anim.slide_in,
                R.anim.fade_out,
                R.anim.fade_in,
                R.anim.slide_out
            )
            replace(R.id.fragmentContainer, fragment, tag)
            addToBackStack(tag)
        }
    }

    private fun backFragment(fragmentTag: String) {
        if(fragmentTag == homeFragmentTag) backHomeFragment()
        else supportFragmentManager.popBackStack(fragmentTag, 0)
    }

    private fun backHomeFragment() {
        supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }

    private fun updateUi() {
        val fragment = currentFragment

        if (fragment is HasCustomTitle) {
            binding.toolbar.title = getString(fragment.getTitleRes())
        } else {
            binding.toolbar.title = getString(R.string.app_name)
        }

        if (supportFragmentManager.backStackEntryCount > 0) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setDisplayShowHomeEnabled(true)
        } else {
            supportActionBar?.setDisplayHomeAsUpEnabled(false)
            supportActionBar?.setDisplayShowHomeEnabled(false)
        }
    }
}