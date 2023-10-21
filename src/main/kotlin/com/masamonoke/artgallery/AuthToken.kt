package com.masamonoke.artgallery

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import java.util.Base64
import java.util.HashMap

fun getTokenFromHeader(header: String): String {
    val tokenStartIdx = 7
    return header.substring(tokenStartIdx)
}

fun decodeToken(token: String): Map<String,String> {
    val chunks = token.split(".")
    val decoder = Base64.getUrlDecoder()
    val payload = String(decoder.decode(chunks[1]))
    val om = ObjectMapper()
    return om.readValue(payload, object: TypeReference<HashMap<String, String>>() {})
}