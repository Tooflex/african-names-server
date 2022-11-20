/*
 * Copyright (c) 2021.
 * @Author Otourou Da Costa
 */

package com.tooflexdev.prenomsafricains.repository

import com.tooflexdev.prenomsafricains.domain.FirstnameTranslation
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface FirstnameTranslationRepository: JpaRepository<FirstnameTranslation, Long>, JpaSpecificationExecutor<FirstnameTranslation> {

    @Query("SELECT ft FROM FirstnameTranslation ft " +
            "inner join ft.firstname ftfn " +
            "where ftfn.id = :firstnameId " +
            "and ft.language.languageCode = :lang")
    fun findByFirstnameAndLanguage(@Param("firstnameId") firstnameId: Long, @Param("lang") lang: String): List<FirstnameTranslation>


    @Query("SELECT ft FROM FirstnameTranslation ft where ft.language.languageCode = :lang")
    fun findByLanguage(@Param("lang") lang: String): List<FirstnameTranslation>

    @Query("SELECT ft FROM FirstnameTranslation ft " +
            "where ft.firstname.id = :firstnameId ")
    fun findByFirstname_Id(@Param("firstnameId") firstnameId: Long): List<FirstnameTranslation>
}