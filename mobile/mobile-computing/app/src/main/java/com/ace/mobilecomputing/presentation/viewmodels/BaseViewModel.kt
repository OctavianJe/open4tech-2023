package com.ace.mobilecomputing.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch


open class BaseViewModel : ViewModel() {
    protected fun runMain(block: suspend () -> Unit) {
        viewModelScope.launch(Dispatchers.Main) {
            block()
        }
    }

    protected fun runBackground(block: suspend () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            block()
        }
    }

    protected suspend fun runAsync(
        blocks: List<() -> Unit>,
        dispatcher: CoroutineDispatcher = Dispatchers.IO
    ) {
        coroutineScope {
            blocks.forEach { block ->
                launch(dispatcher) {
                    block()
                }
            }
        }
    }
}