package com.masamonoke.artgallery.api.service

import com.masamonoke.artgallery.entities.Artwork
import com.masamonoke.artgallery.repo.ArtworkRepo
import org.springframework.stereotype.Service

@Service
class ArtworkService (val artworkRepo: ArtworkRepo) {
    fun  getArtwork(): List<Artwork> {
        return artworkRepo.findAll();
    }

    fun save(artwork: Artwork) {
        artworkRepo.save(artwork)
    }
}