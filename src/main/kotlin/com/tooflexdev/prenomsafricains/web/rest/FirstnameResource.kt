package com.tooflexdev.prenomsafricains.web.rest

import com.sipios.springsearch.anotation.SearchSpec
import com.tooflexdev.prenomsafricains.domain.Firstname
import com.tooflexdev.prenomsafricains.domain.response.FirstnameResponse
import com.tooflexdev.prenomsafricains.service.CsvService
import com.tooflexdev.prenomsafricains.service.FirstnameService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.ArraySchema
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
@CrossOrigin(origins = ["http://localhost:4200", "http://localhost", "http://0.0.0.0", "https://africannames.app"], maxAge=3600)
@RequestMapping("/api/v1/firstnames")
class FirstnameResource(val service: FirstnameService, val csvService: CsvService) {

    @Operation(summary = "Get firstnames list")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Firstnames retrieved",
            content = [Content(mediaType = "application/json",
                schema = Schema(implementation = FirstnameResponse::class))]),
        ApiResponse(responseCode = "400", description = "Bad request", content = [Content()]),
        ApiResponse(responseCode = "404", description = "No firstname found", content = [Content()])]
    )
    @GetMapping("")
    fun findFirstnames(
        @RequestParam(defaultValue = "en") lang: String,
        pageable: Pageable
    ): ResponseEntity<Any?> {
        val firstnamesRetrieved = service.findFirstnames(lang = lang, pageable = pageable)
        return if (firstnamesRetrieved.any()) {
            ResponseEntity(firstnamesRetrieved, HttpStatus.OK)
        } else {
            ResponseEntity<Any?>("Error: No firstname found", HttpStatus.NOT_FOUND)
        }
    }

    @Operation(summary = "Get firstnames list (paginated)")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Firstnames retrieved page by page",
            content = [Content(mediaType = "application/json",
                schema = Schema(implementation = FirstnameResponse::class)
            )]),
        ApiResponse(responseCode = "400", description = "Bad request", content = [Content()]),
        ApiResponse(responseCode = "404", description = "No firstname found", content = [Content()])]
    )
    @GetMapping("/paged")
    fun findPagedFirstnames(
        @RequestParam(defaultValue = "en") lang: String,
        @PageableDefault(page = 0, size = 20) pageable: Pageable
    ): ResponseEntity<Any?> {
         val firstnamesRetrieved = service.findFirstnames(lang = lang, pageable = pageable)
        return if (firstnamesRetrieved.any()) {
            ResponseEntity(firstnamesRetrieved, HttpStatus.OK)
        } else {
            ResponseEntity<Any?>("Error: No firstname found", HttpStatus.NOT_FOUND)
        }
    }

    @Operation(summary = "Get random list of firstnames")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Firstnames retrieved",
            content = [Content(mediaType = "application/json", array = (
                    ArraySchema(schema = Schema(implementation = Firstname::class))
                    ))]),
        ApiResponse(responseCode = "400", description = "Bad request", content = [Content()]),
        ApiResponse(responseCode = "404", description = "No firstname found", content = [Content()])]
    )

    @GetMapping("/random")
    fun findPrenomsAlea(
        @RequestParam(defaultValue = "en") lang: String,
        // Unpaged by default
        @PageableDefault(page = 0, size = 100) pageable: Pageable)
    : ResponseEntity<Any?> {
        val firstnamesRetrieved = service.findRandomFirstnames(lang = lang, pageable = pageable)
        return if (firstnamesRetrieved.any()) {
            ResponseEntity(firstnamesRetrieved, HttpStatus.OK)
        } else {
            ResponseEntity<Any?>("Error: No firstname found", HttpStatus.NOT_FOUND)
        }
    }

    @Operation(summary = "Search firstnames")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Firstnames retrieved",
            content = [Content(mediaType = "application/json", array = (
                    ArraySchema(schema = Schema(implementation = Firstname::class))
                    ))]),
        ApiResponse(responseCode = "400", description = "Bad request", content = [Content()]),
        ApiResponse(responseCode = "404", description = "No firstname found", content = [Content()])]
    )
    @GetMapping("/search")
    fun searchFirstnames(@SearchSpec specs: Specification<Firstname?>?): ResponseEntity<Any?> {
        return try {
            ResponseEntity<Any?>(service.searchFirstnames(specs), HttpStatus.OK)
        } catch (e:IllegalArgumentException) {
            return ResponseEntity<Any?>("Error: Please check search arguments", HttpStatus.BAD_REQUEST)
        } catch (e:Exception) {
            return ResponseEntity<Any?>("Error: Please check search arguments", HttpStatus.BAD_REQUEST)
        }
    }

    @Operation(summary = "Create a firstname")
    @ApiResponses(value = [
        ApiResponse(responseCode = "201", description = "Firstnames created",
            content = [Content(mediaType = "application/json", schema = Schema(implementation = Firstname::class)
                    )]),
        ApiResponse(responseCode = "400", description = "Bad request", content = [Content()]),
        ApiResponse(responseCode = "403", description = "Forbidden", content = [Content()]),
    ]
    )
    @PostMapping("/")
    fun createFirstname(@RequestBody firstname: Firstname): ResponseEntity<Any?>
    = ResponseEntity(service.createFirstname(firstname), HttpStatus.CREATED)

    @Operation(summary = "Import firstnames via .csv file")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Firstnames retrieved",
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
    fun uploadCsvFile(@RequestPart("file") file: MultipartFile): ResponseEntity<MutableIterable<Firstname>> {
        val importedEntries = csvService.uploadCsvFile(file)
        return ResponseEntity.ok(importedEntries)
    }

    @Operation(summary = "Update a firstname")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Firstnames updated",
            content = [Content(mediaType = "application/json", schema = Schema(implementation = Firstname::class)
            )]),
        ApiResponse(responseCode = "400", description = "Bad request", content = [Content()]),
        ApiResponse(responseCode = "404", description = "No firstname found", content = [Content()])]
    )
    @PutMapping("/{id}")
    fun updateFirstname(@PathVariable(value = "id") firstnameId:Long, @RequestBody firstname: Firstname): ResponseEntity<Any?>
    {
        return try {
            ResponseEntity(service.updateFirstname(firstnameId, firstname), HttpStatus.OK)
        } catch (e: NoSuchElementException) {
            ResponseEntity.badRequest().body("Firstname with id $firstnameId doesn't exists")
        }
    }

    @Operation(summary = "Delete a firstname")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Firstnames updated", content = [Content()]),
        ApiResponse(responseCode = "400", description = "Bad request", content = [Content()]),
        ApiResponse(responseCode = "404", description = "No firstname found", content = [Content()])]
    )
    @DeleteMapping("/{id}")
    fun deleteFirstname(@PathVariable(value = "id") firstnameId:Long): ResponseEntity<Any> {
        return ResponseEntity(service.deleteFirstname(firstnameId), HttpStatus.OK)
    }

}