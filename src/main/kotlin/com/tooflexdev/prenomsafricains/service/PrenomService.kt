package com.tooflexdev.prenomsafricains.service

import com.tooflexdev.prenomsafricains.domain.Prenom
import com.tooflexdev.prenomsafricains.repository.PrenomRepository
import org.springframework.stereotype.Service

@Service
class PrenomService(val db: PrenomRepository) {
    fun findFirstnames(): List<Prenom> = db.findAll() as List<Prenom>
    fun findPrenomsAlea(): List<Prenom> = db.findPrenomsAlea()
}

