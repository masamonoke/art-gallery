package com.masamonoke.artgallery.api.auth

data class AuthenticationRequest(
    val name: String,
    val password: String
)
