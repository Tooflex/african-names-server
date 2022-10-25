/*
 * Copyright (c) 2022.
 * @Author Otourou Da Costa
 */

package com.tooflexdev.prenomsafricains.dto

import com.tooflexdev.prenomsafricains.domain.Firstname
import com.tooflexdev.prenomsafricains.domain.FirstnameTranslation
import com.tooflexdev.prenomsafricains.domain.Language
import com.tooflexdev.prenomsafricains.repository.FirstnameRepository
import com.tooflexdev.prenomsafricains.repository.LanguageRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component


@Component
class FirstnameTranslationMapper(val languageRepository: LanguageRepository, val firstnameRepository: FirstnameRepository) {


    fun toDto(firstnameTranslation: FirstnameTranslation): FirstnameTranslationDTO {
        val id: Long = firstnameTranslation.id!!
        val meaningTranslation: String? = firstnameTranslation.meaningTranslation!!
        val originTranslation: String? = firstnameTranslation.originsTranslation!!
        val languageCode: String = firstnameTranslation.language.languageCode!!
        val firstnameId: Long = firstnameTranslation.firstname.id!!
        return FirstnameTranslationDTO(
            id= id,
            meaningTranslation = meaningTranslation,
            originsTranslation= originTranslation,
            languageCode = languageCode,
            firstnameId= firstnameId)
    }

    fun toFirstnameTranslation(firstnameTranslationDTO: FirstnameTranslationDTO): FirstnameTranslation {
        val language: Language = languageRepository.findLanguageByLanguageCode(firstnameTranslationDTO.languageCode!!)
        val firstname: Firstname = firstnameRepository.getReferenceById(firstnameTranslationDTO.firstnameId!!)
        return FirstnameTranslation(
            firstnameTranslationDTO.id,
            meaningTranslation = firstnameTranslationDTO.meaningTranslation,
            originsTranslation = firstnameTranslationDTO.originsTranslation,
            language = language,
            firstname = firstname
        )
    }
}