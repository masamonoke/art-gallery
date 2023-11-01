package com.masamonoke.artgallery.api

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/security_test")
class SecurityTestController {
    @GetMapping
    fun test(): ResponseEntity<String> {
        return ResponseEntity.ok("Hello from secured endpoint")
    }
}
