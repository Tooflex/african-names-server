package com.tooflexdev.prenomsafricains.service

import com.tooflexdev.prenomsafricains.domain.Firstname
import com.tooflexdev.prenomsafricains.repository.FirstnameRepository
import com.tooflexdev.prenomsafricains.repository.FirstnameTranslationRepository
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification
import org.springframework.stereotype.Service
import java.util.NoSuchElementException

@Service
class FirstnameService(val db: FirstnameRepository, val dbFirstnameTranslation: FirstnameTranslationRepository) {
    fun findFirstnames(lang: String = "en", pageable: Pageable): MutableIterable<Firstname> {
        val firstnameList =  db.findAll(pageable)
        if (lang == "en") {
            return firstnameList
        }

        val translations = dbFirstnameTranslation.findByLanguage(lang, pageable)

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

    // Get list of random paginated firstnames
    fun findRandomFirstnames(lang: String = "en", pageable: Pageable = Pageable.ofSize(20)): MutableIterable<Firstname> {

        // Get all records then pick random records by lang from the list with the size of the page and then return the list
        val firstnameList = db.findAll()
        if (pageable.isUnpaged) {
            return firstnameList.shuffled() as MutableIterable<Firstname>
        }
        val randomFirstnames = firstnameList.shuffled().take(pageable.pageSize)
        if (lang == "en") {
            return randomFirstnames as MutableIterable<Firstname>
        }

        val translations = randomFirstnames.map { firstname -> dbFirstnameTranslation.findByLanguageAndFirstnameId(lang, firstname.id) }

        for (firstname in randomFirstnames) {
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

        return randomFirstnames as MutableIterable<Firstname>
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

