package com.masamonoke.artgallery.api.artwork

import com.masamonoke.artgallery.api.decodeToken
import com.masamonoke.artgallery.entities.Artwork
import com.masamonoke.artgallery.api.getTokenFromHeader
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("artwork")
class ArtworkController (val artworkService: ArtworkService) {
    @GetMapping
    fun getArtworks(): List<Artwork> {
        return artworkService.getArtwork()
    }

    @PostMapping("/save")
    fun saveArtwork(@RequestBody request: Map<String, String>, @RequestHeader("Authorization") header: String): ResponseEntity<String> {
        val token = getTokenFromHeader(header)
        val username = decodeToken(token)["sub"]
        return artworkService.save(request, username)
    }

    @DeleteMapping("{artworkId}")
    fun deleteArtwork(@PathVariable artworkId: Int, @RequestHeader("Authorization") header: String): ResponseEntity<String> {
        try {
            artworkService.deleteArtwork(artworkId, header)
        } catch (e: IllegalStateException) {
            return ResponseEntity("$e", HttpStatus.NOT_FOUND)
        } catch (e: SecurityException) {
            return ResponseEntity("$e", HttpStatus.FORBIDDEN)
        } catch (e: NullPointerException) {
            return ResponseEntity("$e", HttpStatus.INTERNAL_SERVER_ERROR)
        }
        return ResponseEntity.ok("Artwork with id=$artworkId successfully deleted")
    }
}