/*
 * Copyright (c) 2021.
 * @Author Otourou Da Costa
 */

package com.tooflexdev.prenomsafricains.service

import com.tooflexdev.prenomsafricains.domain.FirstnameTranslation
import com.tooflexdev.prenomsafricains.repository.FirstnameTranslationRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification
import org.springframework.stereotype.Service


@Service
class FirstnameTranslationService(val dbFirstnameTranslation: FirstnameTranslationRepository) {

    fun findFirstnameTranslations(lang: String, pageable: Pageable): Page<FirstnameTranslation> {
        return if (lang == "*") {
            dbFirstnameTranslation.findAll(pageable)
        } else {
            PageImpl(findFirstnameByLanguage(lang, pageable))
        }
    }

    fun findFirstnameTranslationsByFirstname(firstnameID: Long): List<FirstnameTranslation> {
        return dbFirstnameTranslation.findByFirstname_Id(firstnameID)
    }

    fun findFirstnameTranslationByFirstnameAndLang(firstnameID: Long, lang: String = "en"): List<FirstnameTranslation> {
        return dbFirstnameTranslation.findByFirstnameAndLanguage(firstnameID, lang, PageRequest.of(0, 10))
    }

    fun findFirstnameByLanguage(lang: String = "en", pageable: Pageable): List<FirstnameTranslation> {
        return dbFirstnameTranslation.findByLanguage(lang, pageable)
    }

    fun searchFirstnameTranslations(specs: Specification<FirstnameTranslation?>?): List<FirstnameTranslation> = dbFirstnameTranslation.findAll(Specification.where(specs))

    fun createFirstnameTranslation(firstnameTranslation: FirstnameTranslation): FirstnameTranslation {
        return dbFirstnameTranslation.save(firstnameTranslation)
    }

    @Throws(NoSuchElementException::class)
    fun updateFirstnameTranslation(id: Long, firstnameTranslation: FirstnameTranslation): FirstnameTranslation {
        val firstnameTranslationRetrieved = dbFirstnameTranslation.findById(id).get()
        firstnameTranslationRetrieved.language
        firstnameTranslationRetrieved.meaningTranslation
        firstnameTranslationRetrieved.originsTranslation
        firstnameTranslationRetrieved.firstname

        return dbFirstnameTranslation.save(firstnameTranslationRetrieved)
    }

    fun saveAll(firstnameTranslation: List<FirstnameTranslation>) : MutableIterable<FirstnameTranslation> {
        return dbFirstnameTranslation.saveAll(firstnameTranslation)
    }

}