package com.masamonoke.artgallery.entities

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import java.util.Date

@Entity
data class Artwork (
        @Id
        @GeneratedValue
        var id: Int,
        var created: Date,
        var name: String,

)