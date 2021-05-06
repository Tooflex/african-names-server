package com.tooflexdev.prenomsafricains.service

import com.tooflexdev.prenomsafricains.domain.Firstname
import com.tooflexdev.prenomsafricains.repository.FirstnameRepository
import org.springframework.stereotype.Service

@Service
class FirstnameService(val db: FirstnameRepository) {
    fun findFirstnames(): List<Firstname> = db.findAll() as List<Firstname>
    fun findPrenomsAlea(): List<Firstname> = db.findFirstnamesAlea()
}

