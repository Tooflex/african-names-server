package com.tooflexdev.prenomsafricains.service

import com.tooflexdev.prenomsafricains.domain.Firstname
import com.tooflexdev.prenomsafricains.repository.FirstnameRepository
import org.springframework.data.jpa.domain.Specification
import org.springframework.stereotype.Service

@Service
class FirstnameService(val db: FirstnameRepository) {
    fun findFirstnames(): List<Firstname> = db.findAll()
    fun findPrenomsAlea(): List<Firstname> {
        val list = db.findAll()
        list.shuffle()
        return list
    }
    fun findByCountries(): List<Firstname> = db.findDistinctByCountriesIn(listOf("Ghana", "Senegal", "Ethiopie"))
    fun searchFirstnames(specs: Specification<Firstname?>?): List<Firstname> = db.findAll(Specification.where(specs))
    fun findDistinctOrigins(): List<String> {
        var listOfOrigins = db.findDistinctOrigins()
        var listToReturn = mutableListOf<String>()
        for (origin in listOfOrigins) {
            val originSanitized = origin.replace("[\\n\\t ]", "")
            listToReturn.add(originSanitized)
        }
        return listToReturn
    }
}

