package com.masamonoke.artgallery.repo

import com.masamonoke.artgallery.entities.Artwork
import org.springframework.data.jpa.repository.JpaRepository

interface ArtworkRepo: JpaRepository<Artwork, Int>