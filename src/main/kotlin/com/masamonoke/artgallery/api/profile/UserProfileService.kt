package com.masamonoke.artgallery.api.profile

import org.springframework.http.ResponseEntity

interface UserProfileService {
    fun becomeArtist(username: String?, artistNickname: String): ResponseEntity<String>
}