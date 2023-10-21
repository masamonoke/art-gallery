package com.masamonoke.artgallery.api.profile

import com.masamonoke.artgallery.api.getUserFromHeader
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/profile")
class UserProfileController(val userProfileService: UserProfileService) {

    @PostMapping("/become_artist/{artistNickname}")
    fun becomeArtist(@RequestHeader("Authorization") header: String, @PathVariable artistNickname: String): ResponseEntity<String> {
        val username = getUserFromHeader(header)
        return userProfileService.becomeArtist(username, artistNickname)
    }
}