/*
 * Copyright (c) 2022.
 * @Author Otourou Da Costa
 */

package com.tooflexdev.prenomsafricains.config

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.servers.Server
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class OpenApiConfig {
    @Bean
    fun customOpenAPI(
        @Value("\${app.name}") appTitle: String?,
        @Value("\${app.description}") appDescription: String?,
        @Value("\${app.version}") appVersion: String?,
        @Value("\${app.server-url}") appServerUrl: String?,
        @Value("\${app.server-description}") appServerDescription: String?,

    ): OpenAPI {
        return OpenAPI()
            .info(
                Info()
                    .title(appTitle)
                    .version(appVersion)
                    .description(appDescription)
            )
            .servers(
                mutableListOf(
                    Server()
                    .url(appServerUrl)
                        .description(appServerDescription)
                )
            )
    }
}