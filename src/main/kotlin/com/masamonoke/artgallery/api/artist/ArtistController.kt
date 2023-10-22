package com.masamonoke.artgallery.api.artist

import com.masamonoke.artgallery.api.getUserFromHeader
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/artist")
class ArtistController(val artistService: ArtistService) {
    @PostMapping("/subscribe/{artistNickname}")
    fun subscribe(@RequestHeader("Authorization") header: String, @PathVariable artistNickname: String): ResponseEntity<String>? {
        return getUserFromHeader(header)?.let { artistService.subscribe(it, artistNickname) }
    }
}