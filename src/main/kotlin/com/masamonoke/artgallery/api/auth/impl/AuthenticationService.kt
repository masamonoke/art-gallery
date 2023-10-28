package com.masamonoke.artgallery.api.auth.impl

import com.masamonoke.artgallery.api.auth.AuthenticationRequest
import com.masamonoke.artgallery.api.auth.AuthenticationResponse
import com.masamonoke.artgallery.api.auth.AuthenticationService
import com.masamonoke.artgallery.api.auth.RegisterRequest
import com.masamonoke.artgallery.config.jwt.JwtService
import com.masamonoke.artgallery.entities.user.Role
import com.masamonoke.artgallery.entities.user.User
import com.masamonoke.artgallery.repo.UserRepo
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthenticationService(
    val userRepo: UserRepo,
    val passwordEncoder: PasswordEncoder,
    val jwtService: JwtService,
    val authenticationManager: AuthenticationManager
): AuthenticationService {
    override fun register(request: RegisterRequest): AuthenticationResponse? {
        val user = User(
            name = request.name,
            email = request.email,
            password = passwordEncoder.encode(request.password),
            role = Role.USER
        )
        userRepo.save(user)
        val token = jwtService.generateToken(user)
        return AuthenticationResponse(token)
    }

    override fun authenticate(request: AuthenticationRequest): AuthenticationResponse? {
        val authToken = UsernamePasswordAuthenticationToken(request.name, request.password)
        authenticationManager.authenticate(authToken)
        val user = userRepo.findByName(request.name) ?: return null
        val token = jwtService.generateToken(user)
        return AuthenticationResponse(token)
    }
}