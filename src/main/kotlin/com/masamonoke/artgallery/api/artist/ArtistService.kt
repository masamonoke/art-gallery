package com.masamonoke.artgallery.api.artist

import com.masamonoke.artgallery.repo.ArtistRepo
import com.masamonoke.artgallery.repo.UserRepo
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class ArtistService(val userRepo: UserRepo, val artistRepo: ArtistRepo) {
    fun subscribe(username: String, artistNickname: String): ResponseEntity<String> {
        val user = userRepo.findByName(username) ?: return ResponseEntity("User not found", HttpStatus.NOT_FOUND)
        if (user.subscriptions?.any { it.nickname == artistNickname } == true) {
            return ResponseEntity("User is already subscribed to this artist", HttpStatus.BAD_REQUEST)
        }
        val artist = artistRepo.findByNickname(artistNickname) ?: return ResponseEntity("Artist not found", HttpStatus.NOT_FOUND)
        user.subscriptions?.add(artist)
        artist.subscribers?.add(user)
        userRepo.save(user)
        artistRepo.save(artist)
        return ResponseEntity("Artist subscribed", HttpStatus.OK)
    }
}