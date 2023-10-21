package com.masamonoke.artgallery.entities

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import java.util.Date

@Entity
data class Artwork (
        @Id
        @GeneratedValue
        var id: Int? = null,
        var created: Date? = null,
        var name: String,
        @ManyToOne
        @JoinColumn(name = "artist_id")
        val author: Artist
)