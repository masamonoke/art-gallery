package com.masamonoke.artgallery.entities

import com.fasterxml.jackson.annotation.JsonIgnore
import com.masamonoke.artgallery.entities.user.User
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import jakarta.persistence.OneToOne

@Entity
data class Artist (
    @Id
    @GeneratedValue
    val artistId: Int? = null,
    @OneToOne
    @JoinColumn(name = "user_id")
    val user: User,
    val nickname: String,
    @OneToMany
    @JsonIgnore
    val artworks: Set<Artwork>? = null,
    @OneToMany
    @JsonIgnore
    val subscribers: MutableSet<User>? = HashSet()
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Artist

        return nickname == other.nickname
    }

    override fun hashCode(): Int {
        return nickname.hashCode()
    }
}