package com.tooflexdev.prenomsafricains.web.rest

import com.tooflexdev.prenomsafricains.domain.Prenom
import com.tooflexdev.prenomsafricains.service.PrenomService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class PrenomResource(val service: PrenomService) {
    @GetMapping("/api/v1/firstnames")
    fun findFirstnames(): List<Prenom> = service.findFirstnames()

    @GetMapping("/api/v1/firstnames/random")
    fun findPrenomsAlea(): List<Prenom> = service.findPrenomsAlea()
}