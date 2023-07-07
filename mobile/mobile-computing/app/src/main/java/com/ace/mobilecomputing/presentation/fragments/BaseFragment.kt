package com.ace.mobilecomputing.presentation.fragments

import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavDirections
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.findNavController
import com.ace.mobilecomputing.NavigationHostActivity
import com.ace.mobilecomputing.services.shared_preferences.SharedPreferencesService
import com.ace.mobilecomputing.services.Utilities
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject


abstract class BaseFragment : Fragment() {
    @Inject
    lateinit var sharedPreferencesService: SharedPreferencesService

    @Inject
    lateinit var utilities: Utilities

    override fun onStart() {
        super.onStart()
        setOnBackPressed(null)
    }

    open fun setOnBackPressed(onBackAlternative: (() -> Unit)?) {
        (activity as NavigationHostActivity).onBackPressAlternative = onBackAlternative
    }

    override fun onPause() {
        super.onPause()
        utilities.hideKeyboard(requireView())
    }

    fun Fragment.navigateSafe(directions: NavDirections) {
        val navController = findNavController()
        val destination = navController.currentDestination as FragmentNavigator.Destination
        if (javaClass.name == destination.className) {
            try {
                lifecycleScope.launch(Dispatchers.Main) {
                    navController.navigate(directions)
                }
            } catch (e: Exception) {
                Timber.wtf(e)
            }
        }
    }
}