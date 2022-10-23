package com.parce.shared.network

sealed class ResourceRegister<out T> {
    object Loading : ResourceRegister<Nothing>()
    data class Success<out T>(val data: T) : ResourceRegister<T>()
    data class Failure(val exception: Exception) : ResourceRegister<Nothing>()
}
