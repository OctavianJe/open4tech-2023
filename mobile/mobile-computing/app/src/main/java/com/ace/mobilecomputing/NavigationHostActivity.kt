package com.ace.mobilecomputing


import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil.setContentView
import com.ace.mobilecomputing.databinding.NavigationHostActivityBinding
import com.google.android.gms.location.ActivityRecognitionClient
import com.gu.toolargetool.TooLargeTool
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class NavigationHostActivity : AppCompatActivity() {
    @Inject
    lateinit var client: ActivityRecognitionClient

    var onBackPressAlternative: (() -> Unit)? = null

    private val viewModel: NavigationHostViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView<NavigationHostActivityBinding>(this, R.layout.navigation_host_activity)

        viewModel.requestPermissions(this)
        viewModel.plantTimber(this)
        TooLargeTool.startLogging(this.application)
    }

    override fun onBackPressed() {
        if (onBackPressAlternative != null) {
            onBackPressAlternative!!()
        } else {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.clear()
    }
}
