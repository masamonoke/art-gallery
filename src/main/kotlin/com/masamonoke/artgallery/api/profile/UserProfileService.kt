package com.masamonoke.artgallery.api.profile

import com.masamonoke.artgallery.entities.Artist
import com.masamonoke.artgallery.repo.ArtistRepo
import com.masamonoke.artgallery.repo.UserRepo
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class UserProfileService(val userRepo: UserRepo, val artistRepo: ArtistRepo) {
    fun becomeArtist(username: String?, artistNickname: String): ResponseEntity<String> {
        if (username == null) {
            return ResponseEntity("Username is null", HttpStatus.BAD_REQUEST)
        }
        val user = userRepo.findByName(username) ?: return ResponseEntity("Cannot find user by username", HttpStatus.NOT_FOUND)
        val artist = Artist(user = user, nickname = artistNickname)
        artistRepo.save(artist)
        return ResponseEntity("Artist saved", HttpStatus.OK)
    }
}