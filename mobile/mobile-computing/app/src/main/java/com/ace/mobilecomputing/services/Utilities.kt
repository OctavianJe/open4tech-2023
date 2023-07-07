package com.ace.mobilecomputing.services

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.os.Build
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.FragmentActivity
import com.ace.mobilecomputing.LOGGING_TAG_NAME
import com.ace.mobilecomputing.services.shared_preferences.SharedPreferencesService
import com.google.android.material.snackbar.Snackbar
import timber.log.Timber
import javax.inject.Inject


class Utilities @Inject constructor(
    val context: Context,
    private val sharedPreferencesService: SharedPreferencesService
) {
    fun showSnack(view: View, text: String, color: Int? = null) {
        try {
            val snackBar = Snackbar.make(view, text, Snackbar.LENGTH_SHORT)
            val snackView = snackBar.view
            if (color != null) {
                snackView.setBackgroundColor(color)
            }
            snackBar.show()
        } catch(e: Exception) {
            Timber.tag(LOGGING_TAG_NAME).e("Utilities: showSnack(): View is not valid. Exception: $e")
        }
    }

    fun showExitPopup(activity: FragmentActivity) {
        AlertDialog.Builder(activity)
            .setMessage("Are you sure you want to exit?")
            .setCancelable(false)
            .setPositiveButton("Yes") { _, _ ->
                activity.finish()
            }
            .setNegativeButton("No", null)
            .show()
    }

    fun showKeyboard(view: View) {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.S_V2) {
            (context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager).showSoftInput(
                view,
                0
            )
        }
        else {
            (context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager).toggleSoftInput(
                InputMethodManager.SHOW_FORCED,
                0
            )
        }
    }

    fun hideKeyboard(view: View) {
        (context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromWindow(
            view.windowToken,
            0
        )
    }
}