package com.masamonoke.artgallery.api.artwork

import com.masamonoke.artgallery.entities.Artwork
import org.springframework.http.ResponseEntity

interface ArtworkService {
    fun getArtwork(): List<Artwork>

    fun save(request: Map<String, String>, username: String?): ResponseEntity<String>

    fun deleteArtwork(artworkId: Int, header: String)
}