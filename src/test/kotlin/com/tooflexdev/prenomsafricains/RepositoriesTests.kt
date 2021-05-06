package com.tooflexdev.prenomsafricains

import com.tooflexdev.prenomsafricains.domain.Gender
import com.tooflexdev.prenomsafricains.domain.Firstname
import com.tooflexdev.prenomsafricains.repository.FirstnameRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.data.repository.findByIdOrNull

@DataJpaTest
class RepositoriesTests @Autowired constructor(
        val entityManager: TestEntityManager,
        val firstnameRepository: FirstnameRepository) {

    @Test
    fun `When findByIdOrNull then return Prenom`() {
        val amadou = Firstname(1, "Amadou", Gender.MALE, meaning = "")
        entityManager.persist(amadou)
        entityManager.flush()
        val found = firstnameRepository.findByIdOrNull(amadou.id!!)
        assertThat(found).isEqualTo(amadou)
    }

    @Test
    fun `When findPrenomsAlea then return Prenom List`() {
        val amadou = Firstname(1, "Amadou", Gender.MALE, meaning = "", origins = "", soundURL = "")
        val issa = Firstname(2, "Issa", Gender.MALE, meaning = "Le prénom Issa vient de l'hébreu et signifie « Dieu est généreux ».", origins = "hébraïque, arabe", soundURL = "")
        val moussa = Firstname(3, "Amadou", Gender.MALE, meaning = "En arabe, Moussa signifie \"tiré des eaux\".", origins = "arabe", soundURL = "")
        entityManager.persist(amadou)
        entityManager.persist(issa)
        entityManager.persist(moussa)

        val prenomList = firstnameRepository.findFirstnamesAlea()
        assertThat(prenomList).contains(amadou).contains(issa).contains(moussa)
    }

    @Test
    fun `When findPrenomsAlea and gender not good then return Prenom List`() {

    }



}

