package com.tooflexdev.prenomsafricains

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@EnableScheduling
@SpringBootApplication
class PrenomsafricainsApplication

fun main(args: Array<String>) {
	runApplication<PrenomsafricainsApplication>(*args)
}
