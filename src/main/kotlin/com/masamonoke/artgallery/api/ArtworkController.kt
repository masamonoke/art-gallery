package com.masamonoke.artgallery.api

import com.masamonoke.artgallery.api.service.ArtworkService
import com.masamonoke.artgallery.entities.Artwork
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("artwork")
class ArtworkController (val artworkService: ArtworkService) {
    @GetMapping
    fun getArtworks(): List<Artwork> {
        return artworkService.getArtwork()
    }

    @PostMapping
    fun saveArtwork(@RequestBody artwork: Artwork) {
        artworkService.save(artwork)
    }
}