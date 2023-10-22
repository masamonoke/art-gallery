package com.masamonoke.artgallery.api.profile

import com.masamonoke.artgallery.api.getUserFromHeader
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/profile")
class UserProfileController(val userProfileService: UserProfileService) {

    @PostMapping("/become_artist/{artistNickname}")
    fun becomeArtist(@RequestHeader("Authorization") header: String, @PathVariable artistNickname: String): ResponseEntity<String> {
        val username = getUserFromHeader(header)
        return userProfileService.becomeArtist(username, artistNickname)
    }
}