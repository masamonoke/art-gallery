package com.masamonoke.artgallery.repo

import com.masamonoke.artgallery.entities.Artist
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface ArtistRepo: JpaRepository<Artist, Int> {
    @Query("""
        select a from Artist a
        join User u on a.user.id = u.id
        where u.name = :username
    """)
    fun findByUsername(username: String): Artist?
    fun findByNickname(nickname: String): Artist?
}