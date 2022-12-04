/*
 * Copyright (c) 2021.
 * @Author Otourou Da Costa
 */

package com.tooflexdev.prenomsafricains.web.rest

import com.tooflexdev.prenomsafricains.domain.Firstname
import com.tooflexdev.prenomsafricains.domain.FirstnameTranslation
import com.tooflexdev.prenomsafricains.domain.response.FirstnameTranslationResponse
import com.tooflexdev.prenomsafricains.service.CsvService
import com.tooflexdev.prenomsafricains.service.FirstnameTranslationService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.ArraySchema
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
@CrossOrigin(origins = ["http://localhost:4200", "http://localhost", "http://0.0.0.0", "https://africannames.app"], maxAge=3600)
@RequestMapping("/api/v1/translations")
class FirstnameTranslationResource(val service: FirstnameTranslationService, val csvService: CsvService) {

    @Operation(summary = "Get firstname translations")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Firstname Translations retrieved",
            content = [Content(mediaType = "application/json",
                    schema = Schema(implementation = FirstnameTranslationResponse::class)
                    )]),
        ApiResponse(responseCode = "400", description = "Bad request", content = [Content()]),
        ApiResponse(responseCode = "404", description = "No firstname found", content = [Content()])]
    )
    @GetMapping("")
    fun findFirstnameTranslations(
        @RequestParam(defaultValue = "en") lang: String,
        @PageableDefault(page = 0, size = 20) pageable: Pageable)
    : ResponseEntity<Page<FirstnameTranslation>>
        = ResponseEntity(service.findFirstnameTranslations(pageable), HttpStatus.OK)

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

    @Operation(summary = "Import firstnames via .csv file")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "",
            content = [Content(mediaType = "application/json", array = (
                    ArraySchema(schema = Schema(implementation = Firstname::class))
                    ))]),
        ApiResponse(responseCode = "400", description = "Bad request", content = [Content()]),
        ApiResponse(responseCode = "404", description = "No firstname found", content = [Content()])]
    )
    @RequestMapping(value = ["/import"],
        method = [RequestMethod.POST],
        consumes = [MediaType.MULTIPART_FORM_DATA_VALUE]
    )
    fun uploadCsvFile(@RequestPart("file") file: MultipartFile): ResponseEntity<MutableIterable<FirstnameTranslation>> {
        val importedEntries = csvService.uploadFirstnameTranslationCsvFile(file)
        return ResponseEntity.ok(importedEntries)
    }
}