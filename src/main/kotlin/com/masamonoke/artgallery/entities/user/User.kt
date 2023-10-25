package com.masamonoke.artgallery.entities.user

import com.fasterxml.jackson.annotation.JsonIgnore
import com.masamonoke.artgallery.entities.Artist
import jakarta.persistence.*
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

@Entity
@Table(name="usr")
data class User (
    @Id
    @GeneratedValue
    val id: Int? = null,
    @Column(unique = true)
    val name: String? = null,
    @Column(unique = true)
    val email: String? = null,
    @JsonIgnore
    private val password: String? = null,
    @Enumerated(EnumType.STRING)
    val role: Role? = null,
    @OneToMany
    @JsonIgnore
    val subscriptions: MutableSet<Artist>? = HashSet()
) : UserDetails
{
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return mutableListOf(SimpleGrantedAuthority(role?.name))
    }

    override fun getPassword(): String? {
        return password
    }

    override fun getUsername(): String? {
        return name
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as User

        if (name != other.name) return false
        if (email != other.email) return false
        if (role != other.role) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name?.hashCode() ?: 0
        result = 31 * result + (email?.hashCode() ?: 0)
        result = 31 * result + (role?.hashCode() ?: 0)
        return result
    }
}