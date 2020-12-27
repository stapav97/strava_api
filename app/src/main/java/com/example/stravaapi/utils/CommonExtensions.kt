package com.example.stravaapi.utils

fun Any.logTag(): String {
    return this::class.java.simpleName
}