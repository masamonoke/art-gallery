package com.masamonoke.artgallery.repo

import com.masamonoke.artgallery.entities.user.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepo: JpaRepository<User, Int> {
    fun findByName(name: String): User
    fun findByEmail(email: String): User
}