/*
 * Copyright (c) 2022.
 * @Author Otourou Da Costa
 */

package com.tooflexdev.prenomsafricains.service


import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import twitter4j.Twitter
import twitter4j.TwitterException
import twitter4j.v1.Status
import java.util.*


@Service
class TwitterService {
    @Value("\${twitter.consumerKey}")
    private val twitterConsumerKey: String? = null

    @Value("\${twitter.consumerSecret}")
    private val twitterConsumerSecret: String? = null

    @Value("\${twitter.accessToken}")
    private val twitterAccessToken: String? = null

    @Value("\${twitter.accessTokenSecret}")
    private val twitterAccessTokenSecret: String? = null

    @Throws(TwitterException::class)
    fun postStatus(message: String?): Optional<String> {
        return try {
            val twitter = Twitter.newBuilder()
                .oAuthConsumer(twitterConsumerKey, twitterConsumerSecret)
                .oAuthAccessToken(twitterAccessToken, twitterAccessTokenSecret)
                .build()
            val status: Status = twitter.v1().tweets().updateStatus(message)
            Optional.of(java.lang.String.valueOf(status.id))
        } catch (e: Exception) {
            Optional.of(e.message.toString())
        }
    }
}