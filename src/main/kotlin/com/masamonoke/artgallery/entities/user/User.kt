package com.masamonoke.artgallery.entities.user

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
    val name: String? = null,
    val email: String? = null,
    private val password: String? = null,
    @Enumerated(EnumType.STRING)
    val role: Role? = null
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
}