package com.masamonoke.artgallery.api.artwork

import com.masamonoke.artgallery.entities.Artwork
import com.masamonoke.artgallery.repo.ArtistRepo
import com.masamonoke.artgallery.repo.ArtworkRepo
import com.masamonoke.artgallery.repo.UserRepo
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.util.*

@Service
class ArtworkService (val artworkRepo: ArtworkRepo, val artistRepo: ArtistRepo, val userRepo: UserRepo) {
    fun  getArtwork(): List<Artwork> {
        return artworkRepo.findAll();
    }

    fun save(request: Map<String, String>, username: String?): ResponseEntity<String> {
        if (username == null) {
            return ResponseEntity("Username not provided in token", HttpStatus.NOT_FOUND)
        }
        val artist = artistRepo.findByUsername(username) ?: return ResponseEntity("User is not artist", HttpStatus.BAD_REQUEST)
        val name = request["name"] ?: return ResponseEntity("Artwork name not passed in request body", HttpStatus.BAD_REQUEST)
        val artwork = Artwork(name = name, author = artist, created = Date())
        artworkRepo.save(artwork)
        return ResponseEntity("Artwork saved", HttpStatus.OK)
    }
}