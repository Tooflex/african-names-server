/*
 * Copyright (c) 2022.
 * @Author Otourou Da Costa
 */

package com.tooflexdev.prenomsafricains.repository

import com.tooflexdev.prenomsafricains.domain.Language
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

interface LanguageRepository: JpaRepository<Language, Long>, JpaSpecificationExecutor<Language> {

    fun findLanguageByLanguageCode(languageCode: String): Language
}