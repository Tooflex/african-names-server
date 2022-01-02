/*
 * Copyright (c) 2021.
 * @Author Otourou Da Costa
 */

package com.tooflexdev.prenomsafricains.web.rest

import com.tooflexdev.prenomsafricains.domain.FirstnameTranslation
import com.tooflexdev.prenomsafricains.service.FirstnameTranslationService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin(origins = ["http://localhost:4200"], maxAge=3600)
@RequestMapping("/api/v1/translations")
class FirstnameTranslationResource(val service: FirstnameTranslationService) {

    @GetMapping("")
    fun findFirstnameTranslations(@RequestParam(defaultValue = "en") lang: String): ResponseEntity<List<FirstnameTranslation>>
        = ResponseEntity(service.findFirstnameTranslations(), HttpStatus.OK)

    @GetMapping("firstnames/{id}")
    fun findFirstnameTranslationsByFirstname(@PathVariable(value = "id") firstnameId:Long): ResponseEntity<List<FirstnameTranslation>>
        = ResponseEntity(service.findFirstnameTranslationsByFirstname(firstnameId), HttpStatus.OK)

    @GetMapping("firstnames/{firstnameId}/lang/{langId}")
    fun findFirstnameTranslationByFirstnameAndLang(
        @PathVariable(value = "firstnameId") firstnameId:Long,
        @PathVariable(value = "langId") langId:String): ResponseEntity<List<FirstnameTranslation>>
        = ResponseEntity(service.findFirstnameTranslationByFirstnameAndLang(firstnameId, langId), HttpStatus.OK)
}