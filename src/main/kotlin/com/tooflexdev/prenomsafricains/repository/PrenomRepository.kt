package com.tooflexdev.prenomsafricains.repository

import com.tooflexdev.prenomsafricains.domain.Prenom
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository

interface PrenomRepository: CrudRepository<Prenom, Long> {

    @Query(nativeQuery=true, value="SELECT *  FROM prenom ORDER BY random()") //Use with postgres on small a database
    fun findPrenomsAlea(): List<Prenom>

}