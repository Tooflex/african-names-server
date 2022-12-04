package com.tooflexdev.prenomsafricains.repository

import com.tooflexdev.prenomsafricains.domain.Firstname
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.stereotype.Repository

@Repository
interface FirstnameRepository: JpaRepository<Firstname, Long>, JpaSpecificationExecutor<Firstname> {
}