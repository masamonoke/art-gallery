package com.masamonoke.artgallery.api.artist

import org.springframework.http.ResponseEntity

interface ArtistService {
    fun subscribe(username: String, artistNickname: String): ResponseEntity<String>

    fun unsubscribe(header: String, artistNickname: String): String
}