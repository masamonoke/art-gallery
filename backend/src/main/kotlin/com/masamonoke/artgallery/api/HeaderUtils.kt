package com.masamonoke.artgallery.api

fun getUsernameFromHeader(header: String): String? {
    val token = getTokenFromHeader(header)
    return decodeToken(token)["sub"]
}