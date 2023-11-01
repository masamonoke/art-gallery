package com.masamonoke.artgallery.api.auth

interface AuthenticationService {
    fun register(request: RegisterRequest): AuthenticationResponse?

    fun authenticate(request: AuthenticationRequest): AuthenticationResponse?
}