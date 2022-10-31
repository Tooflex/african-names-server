package com.tooflexdev.prenomsafricains.service

import com.tooflexdev.prenomsafricains.domain.Firstname
import com.tooflexdev.prenomsafricains.repository.FirstnameRepository
import com.tooflexdev.prenomsafricains.repository.FirstnameTranslationRepository
import org.springframework.data.jpa.domain.Specification
import org.springframework.stereotype.Service
import java.util.NoSuchElementException

@Service
class FirstnameService(val db: FirstnameRepository, val dbFirstnameTranslation: FirstnameTranslationRepository) {
    fun findFirstnames(lang: String = "en"): List<Firstname> {
        val firstnameList =  db.findAll()
        if (lang == "en") {
            return firstnameList
        }

        val translations = dbFirstnameTranslation.findByLanguage(lang)

        for (firstname in firstnameList) {
            if (translations.isNotEmpty()) {
                val translation = translations.find { firstnameTranslation -> firstnameTranslation.firstname.id == firstname.id }
                if (translation != null) {
                    firstname.meaning = translation.meaningTranslation
                    firstname.origins = translation.originsTranslation
                }
            } else {
                firstname.meaning = "-"
                firstname.origins = "-"
            }

        }
        return firstnameList
    }

    fun findPrenomsAlea(lang: String = "en"): List<Firstname> {
        val list = db.findAll()
        list.shuffle()

        if (lang == "en") {
            return list
        }

        // Find translations
        for (firstname in list) {
            val translation = dbFirstnameTranslation.findByFirstnameAndLanguage(firstname.id, lang)
            if (translation.isNotEmpty()) {
                firstname.meaning = translation.first().meaningTranslation
                firstname.origins = translation.first().originsTranslation
            }
        }


        return list
    }

    fun searchFirstnames(specs: Specification<Firstname?>?): List<Firstname> = db.findAll(Specification.where(specs))

    fun createFirstname(firstname: Firstname): Firstname {
        return db.save(firstname)
    }

    @Throws(NoSuchElementException::class)
    fun updateFirstname(id: Long, firstname: Firstname): Firstname {
        val firstnameRetrieved = db.findById(id).get()
        firstnameRetrieved.origins = firstname.origins.orEmpty()
        firstnameRetrieved.meaning = firstname.meaning.orEmpty()
        firstnameRetrieved.firstname = firstname.firstname
        firstnameRetrieved.celebrationDate = firstname.celebrationDate.orEmpty()
        firstnameRetrieved.celebrities = firstname.celebrities.orEmpty()
        firstnameRetrieved.countries = firstname.countries.orEmpty()
        firstnameRetrieved.gender = firstname.gender
        firstnameRetrieved.meaningMore = firstname.meaningMore.orEmpty()
        firstnameRetrieved.nearingNames = firstname.nearingNames.orEmpty()
        firstnameRetrieved.regions = firstname.regions.orEmpty()
        firstnameRetrieved.size = firstname.size
        firstnameRetrieved.soundURL = firstname.soundURL.orEmpty()
        return db.save(firstnameRetrieved)
    }

    fun deleteFirstname(id: Long) {
        db.deleteById(id)
    }

    fun saveAll(firstnames: List<Firstname>): List<Firstname> {
        return db.saveAll(firstnames)
    }

    fun findDistinctOrigins(): List<String> {
        return db.findDistinctOrigins()
    }
}

