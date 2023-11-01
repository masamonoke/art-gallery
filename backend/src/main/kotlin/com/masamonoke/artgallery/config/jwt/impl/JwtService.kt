package com.masamonoke.artgallery.config.jwt.impl

import com.masamonoke.artgallery.config.jwt.JwtService
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import java.security.Key
import java.util.*
import java.util.function.Function
import kotlin.collections.HashMap

@Service
class JwtService(@Value("\${secret}") var secret: String): JwtService {

    override fun extractUsername(token: String): String? {
        return extractClaim(token, Claims::getSubject)
    }

    private fun extractClaims(token: String): Claims {
        return Jwts
            .parserBuilder()
            .setSigningKey(getSigningKey())
            .build()
            .parseClaimsJws(token)
            .body
    }

    private fun getSigningKey(): Key {
        val bytes = Decoders.BASE64.decode(secret)
        return Keys.hmacShaKeyFor(bytes)
    }

    private fun<T: Any> extractClaim(token: String, claimsResolver: Function<Claims, T>): T {
        val claims = extractClaims(token)
        return claimsResolver.apply(claims)
    }

    override fun generateToken(extraClaims: Map<String, Any>, userDetails: UserDetails): String {
        return Jwts
            .builder()
            .setClaims(extraClaims)
            .setSubject(userDetails.username)
            .setIssuedAt(Date(System.currentTimeMillis()))
            .setExpiration(Date(System.currentTimeMillis() + 1000 * 60 * 24))
            .signWith(getSigningKey(), SignatureAlgorithm.HS256)
            .compact()
    }

    override fun generateToken(userDetails: UserDetails): String {
        return generateToken(HashMap(), userDetails)
    }

    override fun isTokenValid(token: String, userDetails: UserDetails): Boolean {
        val username = extractUsername(token)
        return (username == userDetails.username) && (!isTokenExpired(token))
    }

    private fun isTokenExpired(token: String): Boolean {
        return extractExpiration(token).before(Date())
    }

    private fun extractExpiration(token: String): Date {
        return extractClaim(token, Claims::getExpiration)
    }
}