package com.ace.mobilecomputing.services.shared_preferences

import android.content.Context
import com.ace.mobilecomputing.R
import com.ace.mobilecomputing.SHARED_PREFERENCES_NAME
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class SharedPreferencesService @Inject constructor(@ApplicationContext private val context: Context) {

    private val sharedPref = context.getSharedPreferences(
        SHARED_PREFERENCES_NAME,
        Context.MODE_PRIVATE
    )

    fun storeAuthenticationToken(token: String) {
        with(sharedPref.edit()) {
            putString(
                context.getString(R.string.shared_pref_auth_token_key),
                token
            )
            apply()
        }
    }

    fun retrieveAuthToken(): String {
        return sharedPref.getString(
            context.getString(R.string.shared_pref_auth_token_key),
            ""
        ) ?: ""
    }
}