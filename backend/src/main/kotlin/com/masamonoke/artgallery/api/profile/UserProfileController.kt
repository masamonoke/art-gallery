package com.masamonoke.artgallery.api.profile

import com.masamonoke.artgallery.api.getUsernameFromHeader
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/profile")
class UserProfileController(val userProfileService: UserProfileService) {

    @PostMapping("/become_artist/{artistNickname}")
    fun becomeArtist(@RequestHeader("Authorization") header: String, @PathVariable artistNickname: String): ResponseEntity<String> {
        val username = getUsernameFromHeader(header)
        return userProfileService.becomeArtist(username, artistNickname)
    }
}