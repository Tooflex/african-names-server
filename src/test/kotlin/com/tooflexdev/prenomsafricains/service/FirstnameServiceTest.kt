/*
 * Copyright (c) 2021. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.tooflexdev.prenomsafricains.service

import com.tooflexdev.prenomsafricains.domain.*
import com.tooflexdev.prenomsafricains.repository.FirstnameRepository
import com.tooflexdev.prenomsafricains.repository.FirstnameTranslationRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable

class FirstnameServiceTest {
    private val firstnameRepository: FirstnameRepository = mockk()
    private val firstnameTranslationRepository: FirstnameTranslationRepository = mockk()

    val firstnameService = FirstnameService(firstnameRepository, firstnameTranslationRepository)

    lateinit var firstname1: Firstname
    lateinit var firstname2: Firstname
    lateinit var firstnames: MutableIterable<Firstname>

    lateinit var frFirstnameTranslations: List<FirstnameTranslation>

    lateinit var page: Page<Firstname>
    lateinit var pageOf1: Page<Firstname>

    @BeforeEach
    fun init() {

        firstname1 = Firstname(
            firstname = "Amadou",
            gender = Gender.MALE,
            meaning = "The name Amadou means Form of Ahmad used in parts of West Africa.",
            size = Size.MEDIUM
        )

        firstname2 = Firstname(
            firstname = "Fatou",
            gender = Gender.FEMALE,
            meaning = "The name Fatou means Form of Fatima used in parts of West Africa.",
            size = Size.SHORT
        )

        firstnames = mutableListOf(firstname1, firstname2)

        page = PageImpl(firstnames.toList())
        pageOf1 = PageImpl(firstnames.toList().subList(0, 1))

        frFirstnameTranslations = listOf(
            FirstnameTranslation(
                firstname = firstname1,
                language = Language("fr", "French", "Français"),
                meaningTranslation = "Le prénom Amadou signifie Forme d'Ahmad utilisée dans certaines parties de l'Afrique de l'Ouest.",
                originsTranslation = "Afrique de l'Ouest"
            ),
            FirstnameTranslation(
                firstname = firstname2,
                language = Language("fr", "French", "Français"),
                meaningTranslation = "Le prénom Fatou signifie Forme de Fatima utilisée dans certaines parties de l'Afrique de l'Ouest.",
                originsTranslation = "Afrique de l'Ouest"
            ))
    }

    @Test
    fun whenGetFirstname_thenReturnFirstname() {
        // given
        every { firstnameRepository.findAll(Pageable.ofSize(20)) } returns page

        // when
        val result = firstnameService.findFirstnames(pageable = Pageable.ofSize(20))

        // then
        verify(exactly = 1) { firstnameRepository.findAll(Pageable.ofSize(20)) }
        assertEquals(page, result)
    }

    @Test
    fun whenGetRandomFirstnamePaged_thenReturnPagedFirstnames() {
        // given
        every { firstnameRepository.findAll(Pageable.unpaged()) } returns page
        every { firstnameRepository.findAll(Pageable.ofSize(20)) } returns page
        every { firstnameRepository.findAll() } returns firstnames.toList()

        // when
        val result = firstnameService.findRandomFirstnames(pageable = Pageable.ofSize(20))

        // then
        assertEquals(result.count(), 2)
        assertEquals(page.size, result.count())
    }
    @Test
    fun whenGetRandomFirstnameUnpaged_thenReturnFirstnames() {
        // given
        every { firstnameRepository.findAll(Pageable.unpaged()) } returns page
        every { firstnameRepository.findAll() } returns firstnames.toList()

        // when
        val result = firstnameService.findRandomFirstnames(pageable = Pageable.unpaged())

        // then
        verify(exactly = 1) { firstnameRepository.findAll() }
        assertEquals(result.count(), 2)
        assertEquals(page.size, result.count())
    }

    @Test
    fun whenGetRandomFirstnameWithPageAndSize_thenReturnFirstnames() {
        // given
        every { firstnameRepository.findAll(Pageable.ofSize(1)) } returns pageOf1
        every { firstnameRepository.findAll() } returns firstnames.toList()

        // when
        val result = firstnameService.findRandomFirstnames(pageable = Pageable.ofSize(1))

        // then
        verify(exactly = 1) { firstnameRepository.findAll() }
        assertEquals(result.count(), 1)
    }

    @Test
    // Run three times to ensure randomness
    fun whenGetRandomFirstnameWithPageAndSize_thenReturnRandomFirstnames() {
        // given
        every { firstnameRepository.findAll(Pageable.ofSize(1)) } returns pageOf1
        every { firstnameRepository.findAll() } returns firstnames.toList()
        //every {  firstnameTranslationRepository.findByLanguageAndFirstnameIds("fr", listOf(firstname1.id, firstname2.id)) } returns frFirstnameTranslations
        //every { firstnameTranslationRepository.findByLanguageAndFirstnameIds("fr", listOf()) } returns listOf()

        // when
        val result1 = firstnameService.findRandomFirstnames("fr", pageable = Pageable.ofSize(1))
        val result2 = firstnameService.findRandomFirstnames("fr", pageable = Pageable.ofSize(1))
        val result3 = firstnameService.findRandomFirstnames("fr", pageable = Pageable.ofSize(1))

        // then
        verify(exactly = 3) { firstnameRepository.findAll() }
        assertEquals(result1.count(), 1)
        assertEquals(result2.count(), 1)
        assertEquals(result3.count(), 1)
    }

    @Test
    fun searchFirstnames() {
    }
}