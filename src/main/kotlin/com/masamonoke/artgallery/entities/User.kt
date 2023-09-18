package com.masamonoke.artgallery.entities

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id

@Entity
data class User (
    @Id
    @GeneratedValue
    val id: Int,
    val name: String,
    val email: String,
    val password: String
)