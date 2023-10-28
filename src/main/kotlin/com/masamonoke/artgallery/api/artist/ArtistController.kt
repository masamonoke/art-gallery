package com.masamonoke.artgallery.api.artist

import com.masamonoke.artgallery.api.getUsernameFromHeader
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/artist")
class ArtistController(val artistService: ArtistService) {
    @PostMapping("/subscribe/{artistNickname}")
    fun subscribe(@RequestHeader("Authorization") header: String, @PathVariable artistNickname: String): ResponseEntity<String>? {
        return getUsernameFromHeader(header)?.let { artistService.subscribe(it, artistNickname) }
    }

    @PostMapping("/unsubscribe/{artistNickname}")
    fun unsubscribe(@RequestHeader("Authorization") header: String, @PathVariable artistNickname: String): ResponseEntity<String> {
        val response: ResponseEntity<String>
		try {
            val res = artistService.unsubscribe(header, artistNickname)
			response = ResponseEntity.ok(res)
        } catch (e: NoSuchFieldException) {
            return ResponseEntity(e.toString(), HttpStatus.BAD_REQUEST)
        } catch (e: UsernameNotFoundException) {
            return ResponseEntity(e.toString(), HttpStatus.NOT_FOUND)
        }
		return response
    }
}
