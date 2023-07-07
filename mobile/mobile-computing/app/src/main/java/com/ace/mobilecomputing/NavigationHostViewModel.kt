package com.ace.mobilecomputing

import android.Manifest
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.ace.mobilecomputing.services.shared_preferences.SharedPreferencesService
import com.ace.mobilecomputing.services.Utilities
import com.ace.mobilecomputing.services.helpers.TimeKeeper
import com.blankj.utilcode.util.PathUtils
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.bipi.tressence.file.FileLoggerTree
import timber.log.Timber
import javax.inject.Inject


@HiltViewModel
class NavigationHostViewModel @Inject constructor(
    val sharedPreferencesService: SharedPreferencesService,
    val utilities: Utilities
) : ViewModel() {
    fun plantTimber(context: Context) {
        val fileName: String = context.getString(R.string.app_name) + "-" + TimeKeeper.now() + "-%g-" + ".log"
        val timberTree: Timber.Tree = FileLoggerTree.Builder()
            .withFileName(fileName)
            .withDirName(PathUtils.getExternalAppDownloadPath() + "/")
            .withSizeLimit(LOGGING_FILE_SIZE_LIMIT)
            .withFileLimit(LOGGING_FILE_NO_LIMIT)
            .withMinPriority(determineLogLevelForFlavour())
            .appendToFile(true)
            .build();

        Timber.plant(timberTree)
    }

    private fun determineLogLevelForFlavour(): Int {
        return when (BuildConfig.FLAVOR) {
            "_local" -> Log.DEBUG
            "_deploy" -> Log.ASSERT
            else -> Log.VERBOSE
        }
    }

    fun requestPermissions(context: Context) {
        TedPermission.with(context)
            .setPermissionListener(permissionListener(context))
            .setDeniedMessage(context.getString(R.string.permission_denied_message))
            .setDeniedCloseButtonText(R.string.generic_cancel)
            .setPermissions(
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
            )
            .check()
    }

    private fun permissionListener(context: Context): PermissionListener = object : PermissionListener {
        override fun onPermissionGranted() {

        }
        override fun onPermissionDenied(deniedPermissions: List<String>) {
            Toast.makeText(context, context.getString(R.string.permission_denied), Toast.LENGTH_SHORT).show()
        }
    }
}