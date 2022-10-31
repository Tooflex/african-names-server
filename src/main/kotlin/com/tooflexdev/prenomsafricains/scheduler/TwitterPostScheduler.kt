/*
 * Copyright (c) 2022.
 * @Author Otourou Da Costa
 */

package com.tooflexdev.prenomsafricains.scheduler

import com.tooflexdev.prenomsafricains.service.FirstnameService
import com.tooflexdev.prenomsafricains.service.TwitterService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class TwitterPostScheduler {

    @Autowired
    private lateinit var twitterService: TwitterService

    @Autowired
    private lateinit var firstnameService: FirstnameService

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
                "\n - more info: africannames://${randomFirstname.firstname}"
    }

    companion object {
        private val logger = LoggerFactory.getLogger(TwitterPostScheduler::class.java)
    }
}