package com.example.domain.models


sealed class MyResult<T> {
    data class Success<T>(val data: T) : MyResult<T>()
    data class Error<T>(val message: T) : MyResult<T>()
}