package com.masamonoke.artgallery.api.artwork.impl

import com.masamonoke.artgallery.api.artwork.ArtworkService
import com.masamonoke.artgallery.api.getUsernameFromHeader
import com.masamonoke.artgallery.entities.Artwork
import com.masamonoke.artgallery.repo.ArtistRepo
import com.masamonoke.artgallery.repo.ArtworkRepo
import com.masamonoke.artgallery.repo.UserRepo
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.util.*

@Service
class ArtworkService (val artworkRepo: ArtworkRepo, val artistRepo: ArtistRepo, val userRepo: UserRepo): ArtworkService {
    override fun getArtwork(): List<Artwork> {
        return artworkRepo.findAll();
    }

    override fun save(request: Map<String, String>, username: String?): ResponseEntity<String> {
        if (username == null) {
            return ResponseEntity("Username not provided in token", HttpStatus.NOT_FOUND)
        }
        val artist = artistRepo.findByUsername(username) ?: return ResponseEntity("User is not artist", HttpStatus.BAD_REQUEST)
        val artworkName = request["name"] ?: return ResponseEntity("Artwork name not passed in request body", HttpStatus.BAD_REQUEST)
        val path = "$username/$artworkName-${UUID.randomUUID()}"
        val artwork = Artwork(name = artworkName, author = artist, created = Date(), path = path)
        try {
            artworkRepo.save(artwork)
        } catch (e: DataIntegrityViolationException) {
            return ResponseEntity("Failed to save artwork: artwork with name $artworkName already exists", HttpStatus.CONFLICT)
        } catch (e: Exception) {
            return ResponseEntity("Failed to save artwork: $e", HttpStatus.INTERNAL_SERVER_ERROR)
        }
        return ResponseEntity("Artwork saved", HttpStatus.OK)
    }

    @Throws(IllegalStateException::class, SecurityException::class, NullPointerException::class)
    override fun deleteArtwork(artworkId: Int, header: String) {
        val username = getUsernameFromHeader(header)
        val user = username?.let { artistRepo.findByUsername(it) }
        user?.let {
            val artwork = artworkRepo.findById(artworkId).orElseThrow { IllegalStateException("Artwork with id=$artworkId not found") }
            if (user.artistId == artwork.author.artistId) {
                artworkRepo.delete(artwork)
                return
            } else {
                throw SecurityException("Artist with id=${user.artistId} is not owner of artwork with id=$artworkId")
            }
        }
        throw NullPointerException("Cannot find user by username=$username")
    }
}