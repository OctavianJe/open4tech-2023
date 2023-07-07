package com.ace.mobilecomputing.domain.network


sealed class ApiRequestResponse<out T : Any> {
    data class Success<out T : Any>(val data: T) : ApiRequestResponse<T>()
    data class Error(val exception: Exception) : ApiRequestResponse<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$exception]"
        }
    }
}
