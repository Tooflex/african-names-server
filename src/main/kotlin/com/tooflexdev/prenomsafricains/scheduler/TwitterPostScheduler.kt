/*
 * Copyright (c) 2022.
 * @Author Otourou Da Costa
 */

package com.tooflexdev.prenomsafricains.scheduler

import com.google.gson.Gson
import com.tooflexdev.prenomsafricains.domain.DeepLinkResponse
import com.tooflexdev.prenomsafricains.domain.Firstname
import com.tooflexdev.prenomsafricains.service.FirstnameService
import com.tooflexdev.prenomsafricains.service.TwitterService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse


@Component
class TwitterPostScheduler {

    @Autowired
    private lateinit var twitterService: TwitterService

    @Autowired
    private lateinit var firstnameService: FirstnameService

    @Value("\${firebase.dynamicLinks.apiKey}")
    private val firebaseApiKey: String? = null

    @Value("\${firebase.dynamicsLinks.urlPrefix}")
    private val firebaseDynamicLinksUrlPrefix: String? = null

    @Value("\${firebase.dynamicLinks.domainUriPrefix}")
    private val firebaseDynamicLinksDomainUriPrefix: String? = null

    @Value("\${firebase.dynamicLinks.androidPackageName}")
    private val firebaseDynamicLinksAndroidPackageName: String? = null

    @Value("\${firebase.dynamicLinks.iosBundleId}")
    private val firebaseDynamicLinksIosBundleId: String? = null

    @Value("\${firebase.dynamicLinks.iosAppStoreId}")
    private val firebaseDynamicLinksIosAppStoreId: String? = null

    @Value("\${firebase.dynamicLinks.iosFallbackLink}")
    private val firebaseDynamicLinksIosFallbackLink: String? = null

    @Value("\${firebase.dynamicLinks.androidFallbackLink}")
    private val firebaseDynamicLinksAndroidFallbackLink: String? = null

    // Every 120 minutes, between 07:00 AM and 10:59 PM
    @Scheduled(cron = "0 */120 7-22 * * *")
    fun postTweet() {
        logger.info(twitterService.postStatus(createTweet()).toString())
    }

    private fun createTweet(): String {
        val randomFirstname = firstnameService.findPrenomsAlea()[0]
        return "${randomFirstname.firstname} " +
                "means \"${randomFirstname.meaning?.trim()}\" " +
                "and it's origins is ${randomFirstname.origins} " +
                "\n ${generateShortDynamicLink(randomFirstname)}"
    }

    private fun generateShortDynamicLink(firstname: Firstname): String {
        val client = HttpClient.newBuilder().build()
        val request = HttpRequest.newBuilder()
            .uri(URI.create("https://firebasedynamiclinks.googleapis.com/v1/shortLinks?key=${firebaseApiKey}".trim()))
            .header("Content-Type", "application/json")
            .POST(
                HttpRequest.BodyPublishers.ofString(
                """
                {
                    "dynamicLinkInfo": {
                        "domainUriPrefix": "\$firebaseDynamicLinksDomainUriPrefix",
                        "link": "${firebaseDynamicLinksUrlPrefix}${firstname.firstname}",
                        "androidInfo": {
                            "androidPackageName": "\$firebaseDynamicLinksAndroidPackageName",
                            "androidFallbackLink": "\$firebaseDynamicLinksAndroidFallbackLink"
                        },
                        "iosInfo": {
                            "iosBundleId": "\$firebaseDynamicLinksIosBundleId",
                            "iosFallbackLink": "\$firebaseDynamicLinksIosFallbackLink",
                            "iosAppStoreId": "\$firebaseDynamicLinksIosAppStoreId"
                        },
                        "socialMetaTagInfo": {
                            "socialTitle": "${firstname.firstname} - African Names App",
                            "socialDescription": "Find out more about ${firstname.firstname} and its meaning",
                        }
                    },
                    "suffix": {
                        "option": "SHORT"
                    }
                }
                """.trimIndent()
            ))
            .build()
        val response = client.send(request, HttpResponse.BodyHandlers.ofString())
        println(response.body())
        val deepLinkResponse = Gson().fromJson(response.body(), DeepLinkResponse::class.java)
        return deepLinkResponse.shortLink
    }


    companion object {
        private val logger = LoggerFactory.getLogger(TwitterPostScheduler::class.java)
    }
}