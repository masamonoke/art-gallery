package com.masamonoke.artgallery.config.jwt

import org.springframework.security.core.userdetails.UserDetails

interface JwtService {
    fun extractUsername(token: String): String?

    fun generateToken(extraClaims: Map<String, Any>, userDetails: UserDetails): String

    fun generateToken(userDetails: UserDetails): String

    fun isTokenValid(token: String, userDetails: UserDetails): Boolean
}