package com.tooflexdev.prenomsafricains.service

import com.tooflexdev.prenomsafricains.domain.Firstname
import com.tooflexdev.prenomsafricains.domain.FirstnameTranslation
import com.tooflexdev.prenomsafricains.repository.FirstnameRepository
import com.tooflexdev.prenomsafricains.repository.FirstnameTranslationRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification
import org.springframework.stereotype.Service
import java.util.NoSuchElementException

@Service
class FirstnameService(val db: FirstnameRepository, val dbFirstnameTranslation: FirstnameTranslationRepository) {
    fun findFirstnames(lang: String = "en", pageable: Pageable): Page<Firstname> {
        // Retrieve the translations for the specified language
        val translations = dbFirstnameTranslation.findByLanguage(lang, pageable)

        // Create a map that maps each firstname ID to its corresponding translation
        val translationMap = translations.associateBy { it.firstname.id }

        // Retrieve the list of firstnames using the provided pagination information
        val firstnameList = db.findAll(pageable)

        // Iterate through the list of firstnames and set the meaning and origins
        // fields to the translation, if available
        for (firstname in firstnameList) {
            val translation = translationMap[firstname.id]
            if (translation != null) {
                firstname.meaning = translation.meaningTranslation
                firstname.origins = translation.originsTranslation
            } else {
                firstname.meaning = null
                firstname.origins = null
            }
        }

        return firstnameList
    }


    // Get list of random paginated firstnames
    fun findRandomFirstnames(lang: String = "en", pageable: Pageable = Pageable.ofSize(20)): List<Firstname> {
        // Retrieve all the firstnames from the database
        val firstnameList = db.findAll()

        // If the pageable is unpaged, return a shuffled list of all firstnames
        if (pageable.isUnpaged) {
            return firstnameList.shuffled()
        }

        // Otherwise, take the specified number of random firstnames from the list
        val randomFirstnames = firstnameList.shuffled().take(pageable.pageSize)

        // Retrieve the translations for the specified language and firstnames
        val translations = dbFirstnameTranslation.findByLanguageAndFirstnameIds(lang, randomFirstnames.map(Firstname::id))

        // Create a map that maps each firstname ID to its corresponding translation
        val translationMap = translations.associateBy { it.firstname.id }

        // Iterate through the list of random firstnames and set the meaning and origins
        // fields to the translation, if available
        for (firstname in randomFirstnames) {
            val translation = translationMap[firstname.id]
            if (translation != null) {
                firstname.meaning = translation.meaningTranslation
                firstname.origins = translation.originsTranslation
            } else {
                firstname.meaning = null
                firstname.origins = null
            }
        }

        return randomFirstnames
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

    fun saveAll(firstnames: List<Firstname>): MutableIterable<Firstname> {
        return db.saveAll(firstnames)
    }
}

