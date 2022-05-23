/*
 * Copyright (c) 2021.
 * @Author Otourou Da Costa
 */

package com.tooflexdev.prenomsafricains.repository

import com.tooflexdev.prenomsafricains.domain.Firstname
import com.tooflexdev.prenomsafricains.domain.FirstnameTranslation
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface FirstnameTranslationRepository: JpaRepository<FirstnameTranslation, Long>, JpaSpecificationExecutor<FirstnameTranslation> {

    @Query("SELECT ft FROM FirstnameTranslation ft " +
            "inner join ft.firstname ftfn " +
            "where ftfn.id = :firstnameId " +
            "and ft.language.languageCode = :lang")
    fun findByFirstnameAndLanguage(firstnameId: Long, lang: String): List<FirstnameTranslation>

    @Query("SELECT ft FROM FirstnameTranslation ft where ft.language.languageCode = :lang")
    fun findByLanguage(lang: String): List<FirstnameTranslation>

    fun findByFirstname_Id(firstnameId: Long): List<FirstnameTranslation>
}