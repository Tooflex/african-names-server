package com.tooflexdev.prenomsafricains.web.rest

import com.sipios.springsearch.anotation.SearchSpec
import com.tooflexdev.prenomsafricains.domain.Firstname
import com.tooflexdev.prenomsafricains.service.FirstnameService
import org.springframework.data.jpa.domain.Specification
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class FirstnameResource(val service: FirstnameService) {
    @GetMapping("/api/v1/firstnames")
    fun findFirstnames(): List<Firstname> = service.findFirstnames()

    @GetMapping("/api/v1/firstnames/random")
    fun findPrenomsAlea(): List<Firstname> = service.findPrenomsAlea()

    @GetMapping("/api/v1/firstnames/search")
    fun searchFirstnames(@SearchSpec specs: Specification<Firstname?>?): ResponseEntity<Any?> {
        return try {
            ResponseEntity<Any?>(service.searchFirstnames(specs), HttpStatus.OK)
        } catch (e:IllegalArgumentException) {
            return ResponseEntity<Any?>("Error: Please check search arguments", HttpStatus.BAD_REQUEST)
        } catch (e:Exception) {
            return ResponseEntity<Any?>("Error: Please check search arguments", HttpStatus.BAD_REQUEST)
        }
    }
}