package com.tooflexdev.prenomsafricains.repository

import com.tooflexdev.prenomsafricains.domain.Firstname
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.rest.core.annotation.RepositoryRestResource

@RepositoryRestResource
interface FirstnameRepository: JpaRepository<Firstname, Long>, JpaSpecificationExecutor<Firstname> {
}