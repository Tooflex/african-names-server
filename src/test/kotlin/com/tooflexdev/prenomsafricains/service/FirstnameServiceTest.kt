/*
 * Copyright (c) 2021. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.tooflexdev.prenomsafricains.service

import com.tooflexdev.prenomsafricains.domain.Firstname
import com.tooflexdev.prenomsafricains.domain.Gender
import com.tooflexdev.prenomsafricains.domain.Size
import com.tooflexdev.prenomsafricains.repository.FirstnameRepository
import com.tooflexdev.prenomsafricains.repository.FirstnameTranslationRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class FirstnameServiceTest {
    private val firstnameRepository: FirstnameRepository = mockk()
    private val firstnameTranslationRepository: FirstnameTranslationRepository = mockk()

    val firstnameService = FirstnameService(firstnameRepository, firstnameTranslationRepository)

    lateinit var firstname1: Firstname
    lateinit var firstname2: Firstname
    lateinit var firstnames: List<Firstname>

    @BeforeEach
    fun init() {

        firstname1 = Firstname(
            firstname = "Amadou",
            gender = Gender.MALE,
            meaning = "",
            size = Size.MEDIUM
        )

        firstname2 = Firstname(
            firstname = "Fatou",
            gender = Gender.FEMALE,
            meaning = "",
            size = Size.SHORT
        )

        firstnames = arrayListOf(firstname1, firstname2)
    }

    @Test
    fun whenGetFirstname_thenReturnFirstname() {
        // given
        every { firstnameRepository.findAll() } returns firstnames

        // when
        val result = firstnameService.findFirstnames()

        // then
        verify(exactly = 1) { firstnameRepository.findAll() }
        assertEquals(firstnames, result)
    }

    @Test
    fun findPrenomsAlea() {
        // given
        every { firstnameRepository.findAll() } returns firstnames

        // when
            val result = firstnameService.findPrenomsAlea()

        // then
        assertEquals(result.size, 2)
        assertEquals(firstnames.size, result.size)
    }

    @Test
    fun searchFirstnames() {
    }
}