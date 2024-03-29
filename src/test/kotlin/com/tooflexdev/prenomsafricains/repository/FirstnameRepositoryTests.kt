/*
 * Copyright (c) 2022.
 * @Author Otourou Da Costa
 */

package com.tooflexdev.prenomsafricains.repository

import com.tooflexdev.prenomsafricains.domain.Gender
import com.tooflexdev.prenomsafricains.domain.Firstname
import com.tooflexdev.prenomsafricains.domain.Size
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.data.repository.findByIdOrNull

@DataJpaTest
class FirstnameRepositoryTests @Autowired constructor(
    val entityManager: TestEntityManager,
    val firstnameRepository: FirstnameRepository
) {

    @Test
    fun `When findByIdOrNull then return Prenom`() {
        val amadou = Firstname()
        amadou.firstname = "Amadou"
        amadou.gender = Gender.MALE
        amadou.meaning = ""
        amadou.size=Size.MEDIUM
        entityManager.persist(amadou)
        entityManager.flush()
        val found = firstnameRepository.findByIdOrNull(amadou.id)
        assertThat(found).isEqualTo(amadou)
    }
}

