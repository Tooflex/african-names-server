/*
 * Copyright (c) 2022.
 * @Author Otourou Da Costa
 */

package com.tooflexdev.prenomsafricains.web.rest

import com.tooflexdev.prenomsafricains.domain.request.SubscribeNewsletterRequest
import com.tooflexdev.prenomsafricains.domain.response.MembersResponse
import com.tooflexdev.prenomsafricains.service.MailChimpService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.parameters.RequestBody
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@CrossOrigin(origins = ["localhost:4200", "http://localhost", "http://0.0.0.0", "https://africannames.app"], maxAge = 3600)
@RequestMapping("/api/v1/newsletter")
class MailChimpResource(val mailChimpService: MailChimpService) {

    @Operation(summary = "Subscribe to African Name App newsletter")
    @PostMapping("/subscribe")
    fun subscribeToNewsletter(@RequestBody subscribeNewsletterRequest: @Valid SubscribeNewsletterRequest): ResponseEntity<*> {
        return ResponseEntity.ok<Boolean>(
            mailChimpService.subscribeToList(
                subscribeNewsletterRequest.email,
                subscribeNewsletterRequest.firstName,
                subscribeNewsletterRequest.lastName)
        )
    }

    @Operation(summary = "Ping MailChimp API")
    @GetMapping("/ping")
    fun ping(): ResponseEntity<*> {
        return ResponseEntity.ok<Boolean>(mailChimpService.pingServer())
    }

    @Operation(summary = "Get all members of the newsletter list")
    @GetMapping("/members")
    fun getSubscribers(): ResponseEntity<*> {
        return ResponseEntity.ok<MembersResponse>(mailChimpService.getSubscribers())
    }
}