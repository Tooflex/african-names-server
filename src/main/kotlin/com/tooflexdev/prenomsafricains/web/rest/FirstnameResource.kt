package com.tooflexdev.prenomsafricains.web.rest

import com.tooflexdev.prenomsafricains.domain.Firstname
import com.tooflexdev.prenomsafricains.service.FirstnameService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class FirstnameResource(val service: FirstnameService) {
    @GetMapping("/api/v1/firstnames")
    fun findFirstnames(): List<Firstname> = service.findFirstnames()

    @GetMapping("/api/v1/firstnames/random")
    fun findPrenomsAlea(): List<Firstname> = service.findPrenomsAlea()
}