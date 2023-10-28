package com.masamonoke.artgallery.api.artist.impl

import com.masamonoke.artgallery.api.artist.ArtistService
import com.masamonoke.artgallery.api.getUsernameFromHeader
import com.masamonoke.artgallery.repo.ArtistRepo
import com.masamonoke.artgallery.repo.UserRepo
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class ArtistService(val userRepo: UserRepo, val artistRepo: ArtistRepo): ArtistService {
    override fun subscribe(username: String, artistNickname: String): ResponseEntity<String> {
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

    @Throws(NoSuchFieldException::class, UsernameNotFoundException::class)
    override fun unsubscribe(header: String, artistNickname: String): String {
        val username = getUsernameFromHeader(header) ?: throw NoSuchFieldException("Cannot get username from token")
        val user = userRepo.findByName(username) ?: throw UsernameNotFoundException("Cannot find user with name $username")
        val artist = artistRepo.findByNickname(artistNickname) ?: throw UsernameNotFoundException("Cannot find artist with name $username")
        if (user.subscriptions?.find { it == artist } != null) {
            user.subscriptions.remove(artist)
            artist.subscribers?.remove(user)
			userRepo.save(user);
			artistRepo.save(artist);
        } else {
			return "User is not subscribed to $artistNickname"
		}
		return "User unsubscribed from $artistNickname"
    }
}
