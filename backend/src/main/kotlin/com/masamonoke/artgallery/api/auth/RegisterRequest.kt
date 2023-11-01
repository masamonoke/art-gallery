package com.masamonoke.artgallery.api.auth

data class RegisterRequest(
    val name: String,
    val email: String,
    val password: String
)
