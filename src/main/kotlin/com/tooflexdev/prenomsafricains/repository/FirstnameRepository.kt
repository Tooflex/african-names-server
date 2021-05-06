package com.tooflexdev.prenomsafricains.repository

import com.tooflexdev.prenomsafricains.domain.Firstname
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository

interface FirstnameRepository: CrudRepository<Firstname, Long> {

    @Query(nativeQuery=true, value="SELECT *  FROM firstname ORDER BY random()") //Use with postgres on small a database
    fun findFirstnamesAlea(): List<Firstname>

}