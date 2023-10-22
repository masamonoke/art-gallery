package com.masamonoke.artgallery.api

fun getUserFromHeader(header: String): String? {
    val token = getTokenFromHeader(header)
    return decodeToken(token)["sub"]
}