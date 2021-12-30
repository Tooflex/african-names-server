package com.tooflexdev.prenomsafricains.web.rest

import com.sipios.springsearch.anotation.SearchSpec
import com.tooflexdev.prenomsafricains.domain.Firstname
import com.tooflexdev.prenomsafricains.service.CsvService
import com.tooflexdev.prenomsafricains.service.FirstnameService
import org.springframework.data.jpa.domain.Specification
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
@CrossOrigin(origins = ["http://localhost:4200"], maxAge=3600)
@RequestMapping("/api/v1/firstnames")
class FirstnameResource(val service: FirstnameService, val csvService: CsvService) {

    @GetMapping("")
    fun findFirstnames(@RequestParam(defaultValue = "en") lang: String)
        : ResponseEntity<List<Firstname>> = ResponseEntity(service.findFirstnames(lang = lang), HttpStatus.OK)

    @GetMapping("/random")
    fun findPrenomsAlea(@RequestParam(defaultValue = "en") lang: String): List<Firstname> = service.findPrenomsAlea()

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

    @PostMapping("/")
    fun createFirstname(@RequestBody firstname: Firstname): ResponseEntity<Firstname>
    = ResponseEntity(service.createFirstname(firstname), HttpStatus.CREATED)

    @RequestMapping(value = ["/import"],
        method = [RequestMethod.POST],
        consumes = [MediaType.MULTIPART_FORM_DATA_VALUE]
    )
    fun uploadCsvFile(@RequestPart("file") file: MultipartFile): ResponseEntity<List<Firstname>> {
        val importedEntries = csvService.uploadCsvFile(file)
        return ResponseEntity.ok(importedEntries)
    }

    @PutMapping("/{id}")
    fun updateFirstname(@PathVariable(value = "id") firstnameId:Long, @RequestBody firstname: Firstname): ResponseEntity<Any?>
    {
        return try {
            ResponseEntity(service.updateFirstname(firstnameId, firstname), HttpStatus.OK)
        } catch (e: NoSuchElementException) {
            ResponseEntity.badRequest().body("Firstname with id $firstnameId doesn't exists")
        }
    }

    @DeleteMapping("/{id}")
    fun deleteFirstname(@PathVariable(value = "id") firstnameId:Long): ResponseEntity<Any> {
        return ResponseEntity(service.deleteFirstname(firstnameId), HttpStatus.OK)
    }

}