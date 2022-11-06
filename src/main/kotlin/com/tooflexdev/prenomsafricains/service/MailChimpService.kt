/*
 * Copyright (c) 2022.
 * @Author Otourou Da Costa
 */

package com.tooflexdev.prenomsafricains.service

import com.google.gson.Gson
import com.tooflexdev.prenomsafricains.domain.response.MembersResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import java.util.*

@Service
class MailChimpService {
    @Value("\${mailchimp.apiKey}")
    private val mailChimpApiKey: String? = null

    @Value("\${mailchimp.listId}")
    private val mailChimpListId: String? = null

    @Value("\${mailchimp.dc}")
    private val mailChimpDc: String? = null

    private fun getBasicAuthenticationHeader(username: String, password: String): String {
        val valueToEncode = "$username:$password"
        return "Basic " + Base64.getEncoder().encodeToString(valueToEncode.toByteArray())
    }


    fun pingServer(): Boolean {
        val client = HttpClient.newHttpClient()

        val request = HttpRequest.newBuilder()
            .uri(URI.create("https://$mailChimpDc.api.mailchimp.com/3.0/ping"))
            .header("Content-Type", "application/json")
            .header("Authorization", getBasicAuthenticationHeader("anystring", "$mailChimpApiKey"))
            .build()

        val response = client.send(request, HttpResponse.BodyHandlers.ofString())

        return response.statusCode() == 200
    }

    fun subscribeToList(email: String, firstName: String?, lastName: String?): Boolean {
        val client = HttpClient.newBuilder().build()
        val request = HttpRequest.newBuilder()
            .uri(URI.create("https://${mailChimpDc}.api.mailchimp.com/3.0/lists/$mailChimpListId/members"))
            .header("Content-Type", "application/json")
            .header("Authorization", getBasicAuthenticationHeader("anystring", "$mailChimpApiKey"))
            .POST(HttpRequest.BodyPublishers.ofString(
                """
                {
                    "email_address": "$email",
                    "status": "subscribed",
                    "merge_fields": {
                        "FNAME": "$firstName",
                        "LNAME": "$lastName"
                    }
                }
                """.trimIndent()
            ))
            .build()
        val isOK = client.send(request, HttpResponse.BodyHandlers.ofString())
        return isOK.statusCode() == 200
    }

    fun getSubscribers(): MembersResponse {
        val gson = Gson()
        val client = HttpClient.newBuilder().build()
        val request = HttpRequest.newBuilder()
            .uri(URI.create("https://${mailChimpDc}.api.mailchimp.com/3.0/lists/$mailChimpListId/members"))
            .header("Content-Type", "application/json")
            .header("Authorization", getBasicAuthenticationHeader("anystring", "$mailChimpApiKey"))
            .GET()
            .build()
        val response = client.send(request, HttpResponse.BodyHandlers.ofString())
        return gson.fromJson(response.body(), MembersResponse::class.java)
    }
}