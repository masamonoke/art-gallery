package com.masamonoke.artgallery.entities

import jakarta.persistence.Column
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
        @Column(unique = true)
        var name: String,
        @ManyToOne
        @JoinColumn(name = "artist_id")
        val author: Artist,
        val path: String,
        val title: String? = null
)