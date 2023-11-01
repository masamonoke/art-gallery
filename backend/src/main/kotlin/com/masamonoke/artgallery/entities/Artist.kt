package com.masamonoke.artgallery.entities

import com.fasterxml.jackson.annotation.JsonIgnore
import com.masamonoke.artgallery.entities.user.User
import jakarta.persistence.*

@Entity
data class Artist (
    @Id
    @GeneratedValue
    val artistId: Int? = null,
    @OneToOne
    @JoinColumn(name = "user_id")
    val user: User,
    @Column(unique = true)
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