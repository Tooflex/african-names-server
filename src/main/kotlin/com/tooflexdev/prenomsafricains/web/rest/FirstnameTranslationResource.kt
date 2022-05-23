/*
 * Copyright (c) 2021.
 * @Author Otourou Da Costa
 */

package com.tooflexdev.prenomsafricains.web.rest

import com.tooflexdev.prenomsafricains.domain.FirstnameTranslation
import com.tooflexdev.prenomsafricains.service.FirstnameTranslationService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.ArraySchema
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin(origins = ["http://localhost:4200", "http://localhost", "http://0.0.0.0", "https://africannames.app"], maxAge=3600)
@RequestMapping("/api/v1/translations")
class FirstnameTranslationResource(val service: FirstnameTranslationService) {

    @Operation(summary = "Get firstname translations")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Firstname Translations retrieved",
            content = [Content(mediaType = "application/json", array = (
                    ArraySchema(schema = Schema(implementation = FirstnameTranslation::class))
                    ))]),
        ApiResponse(responseCode = "400", description = "Bad request", content = [Content()]),
        ApiResponse(responseCode = "404", description = "No firstname found", content = [Content()])]
    )
    @GetMapping("")
    fun findFirstnameTranslations(@RequestParam(defaultValue = "en") lang: String): ResponseEntity<List<FirstnameTranslation>>
        = ResponseEntity(service.findFirstnameTranslations(), HttpStatus.OK)

    @Operation(summary = "Get firstname translations")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Get firstname by Id",
            content = [Content(mediaType = "application/json", array = (
                    ArraySchema(schema = Schema(implementation = FirstnameTranslation::class))
                    ))]),
        ApiResponse(responseCode = "400", description = "Bad request", content = [Content()]),
        ApiResponse(responseCode = "404", description = "No firstname found", content = [Content()])]
    )
    @GetMapping("firstnames/{id}")
    fun findFirstnameTranslationsByFirstname(@PathVariable(value = "id") firstnameId:Long): ResponseEntity<List<FirstnameTranslation>>
        = ResponseEntity(service.findFirstnameTranslationsByFirstname(firstnameId), HttpStatus.OK)

    @Operation(summary = "Get firstname translations")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Get firstname by Id",
            content = [Content(mediaType = "application/json", array = (
                    ArraySchema(schema = Schema(implementation = FirstnameTranslation::class))
                    ))]),
        ApiResponse(responseCode = "400", description = "Bad request", content = [Content()]),
        ApiResponse(responseCode = "404", description = "No firstname found", content = [Content()])]
    )
    @GetMapping("firstnames/{firstnameId}/lang/{langId}")
    fun findFirstnameTranslationByFirstnameAndLang(
        @PathVariable(value = "firstnameId") firstnameId:Long,
        @PathVariable(value = "langId") langId:String): ResponseEntity<List<FirstnameTranslation>>
        = ResponseEntity(service.findFirstnameTranslationByFirstnameAndLang(firstnameId, langId), HttpStatus.OK)
}