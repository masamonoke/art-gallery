package com.masamonoke.artgallery.api

import com.masamonoke.artgallery.decodeToken
import com.masamonoke.artgallery.getTokenFromHeader

fun getUserFromHeader(header: String): String? {
    val token = getTokenFromHeader(header)
    return decodeToken(token)["sub"]
}