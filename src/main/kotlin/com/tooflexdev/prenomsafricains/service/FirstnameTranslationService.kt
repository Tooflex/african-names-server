/*
 * Copyright (c) 2021.
 * @Author Otourou Da Costa
 */

package com.tooflexdev.prenomsafricains.service

import com.tooflexdev.prenomsafricains.domain.FirstnameTranslation
import com.tooflexdev.prenomsafricains.repository.FirstnameTranslationRepository
import org.springframework.data.jpa.domain.Specification
import org.springframework.stereotype.Service
import java.util.NoSuchElementException

@Service
class FirstnameTranslationService(val dbFirstnameTranslation: FirstnameTranslationRepository) {

    fun findFirstnameTranslations(): List<FirstnameTranslation> {
        return dbFirstnameTranslation.findAll()
    }

    fun findFirstnameTranslationsByFirstname(firstnameID: Long): List<FirstnameTranslation> {
        return dbFirstnameTranslation.findByFirstname_Id(firstnameID)
    }

    fun findFirstnameTranslationByFirstnameAndLang(firstnameID: Long, lang: String = "en"): List<FirstnameTranslation> {
        return dbFirstnameTranslation.findByFirstnameAndLanguage(firstnameID, lang)
    }

    fun findFirstnameByLanguage(lang: String = "en"): List<FirstnameTranslation> {
        return dbFirstnameTranslation.findByLanguage(lang)
    }

    fun searchFirstnameTranslations(specs: Specification<FirstnameTranslation?>?): List<FirstnameTranslation> = dbFirstnameTranslation.findAll(Specification.where(specs))

    fun createFirstnameTranslation(firstnameTranslation: FirstnameTranslation): FirstnameTranslation {
        return dbFirstnameTranslation.save(firstnameTranslation)
    }

    @Throws(NoSuchElementException::class)
    fun updateFirstnameTranslation(id: Long, firstnameTranslation: FirstnameTranslation): FirstnameTranslation {
        var firstnameTranslationRetrieved = dbFirstnameTranslation.findById(id).get()
        firstnameTranslationRetrieved.language
        firstnameTranslationRetrieved.meaningTranslation
        firstnameTranslationRetrieved.originsTranslation
        firstnameTranslationRetrieved.firstname

        return dbFirstnameTranslation.save(firstnameTranslationRetrieved)
    }

    fun saveAll(firstnameTranslation: List<FirstnameTranslation>) : List<FirstnameTranslation> {
        return dbFirstnameTranslation.saveAll(firstnameTranslation)
    }

}