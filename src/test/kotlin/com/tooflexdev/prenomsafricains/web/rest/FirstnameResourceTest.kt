/*
 * Copyright (c) 2022.
 * @Author Otourou Da Costa
 */

package com.tooflexdev.prenomsafricains.web.rest

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.json.JsonMapper
import com.ninjasquad.springmockk.MockkBean
import com.tooflexdev.prenomsafricains.domain.Firstname
import com.tooflexdev.prenomsafricains.domain.Gender
import com.tooflexdev.prenomsafricains.domain.Size
import com.tooflexdev.prenomsafricains.service.CsvService
import com.tooflexdev.prenomsafricains.service.FirstnameService
import com.tooflexdev.prenomsafricains.service.FirstnameTranslationService
import com.tooflexdev.prenomsafricains.service.MailChimpService
import io.mockk.every
import org.hamcrest.CoreMatchers.containsString
import org.hamcrest.collection.IsCollectionWithSize.hasSize
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.MediaType
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@ExtendWith(SpringExtension::class)
@WebMvcTest
class FirstnameResourceTest @Autowired constructor(
    var mockMvc: MockMvc,
    //private val webApplicationContext: WebApplicationContext
    ) {

    @MockkBean
    lateinit var firstnameService: FirstnameService

    @MockkBean
    lateinit var firstnameCsvService: CsvService

    @MockkBean
    lateinit var firstnameTranslationService: FirstnameTranslationService

    @MockkBean
    lateinit var mailChimpService: MailChimpService

    private lateinit var firstname1: Firstname
    private lateinit var firstname2: Firstname
    private lateinit var firstname3: Firstname
    private lateinit var firstnames: Page<Firstname>
    private lateinit var firstnamesOf1Element: Page<Firstname>
    private lateinit var firstnamesOf3Elements: Page<Firstname>
    private lateinit var firstName1Json: String

    @BeforeEach
    fun init() {

        firstname1 = Firstname(
            id = 0,
            firstname = "Amadou",
            gender = Gender.MALE,
            meaning = "",
            size = Size.MEDIUM)

        firstname2 = Firstname(
            id = 1,
            firstname = "Fatou",
            gender = Gender.FEMALE,
            meaning = "",
            size = Size.SHORT)

        firstname3 = Firstname(
            id = 2,
            firstname = "Eniola",
            gender = Gender.FEMALE,
            meaning = "",
            size = Size.SHORT)

        firstnames = listOf(firstname1, firstname2) as Page<Firstname>
        firstnamesOf1Element = listOf(firstname1) as Page<Firstname>
        firstnamesOf3Elements = listOf(firstname1, firstname2, firstname3) as Page<Firstname>

        val objectMapper: ObjectMapper = JsonMapper.builder()
            .findAndAddModules()
            .build()
        firstName1Json = objectMapper.writeValueAsString(firstname1)
    }

    @Test
    @WithMockUser(username = "myUser", roles = ["USER"])
    fun givenNoExistingFirstnames_whenGetRequest_thenReturnsFirstnameJsonWithStatus404() {
        every { firstnameService.findFirstnames(pageable = Pageable.ofSize(20)) } returns Page.empty()
        every { firstnameService.findFirstnames(pageable = Pageable.unpaged()) } returns Page.empty()

        mockMvc.perform(get("/api/v1/firstnames/paged"))
            .andExpect(status().isNotFound)
            .andExpect(content().string(containsString("No firstname found")))
    }

    @Test
    fun givenExistingFirstnamesAndUnauthorizedUser_whenGetRequest_thenReturnsFirstnameJsonWithStatus401() {
        every { firstnameService.findFirstnames(pageable = Pageable.unpaged()) } returns firstnames

        mockMvc.perform(get("/api/v1/firstnames/paged"))
            .andExpect(status().isUnauthorized)
    }

    @Test
    @WithMockUser(username = "myUser", roles = ["USER"])
    fun givenExistingFirstnames_whenGetRequest_thenReturnsFirstnameJsonWithStatus200() {
        every { firstnameService.findFirstnames(pageable = Pageable.ofSize(20)) } returns firstnames
        every { firstnameService.findFirstnames(pageable = Pageable.unpaged()) } returns firstnames

        mockMvc.perform(get("/api/v1/firstnames/paged"))
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.*", hasSize<Firstname>(2)))
            .andExpect(jsonPath("$[0].firstname").value("Amadou"))
            .andExpect(jsonPath("$[1].firstname").value("Fatou"))
    }

    @Test
    @WithMockUser(username = "myUser", roles = ["USER"])
    fun givenExistingFirstnames_whenFindPrenomsAlea_thenReturnsFirstnameJsonWithStatus200() {
        every { firstnameService.findRandomFirstnames() } returns firstnames.toList()
        every { firstnameService.findRandomFirstnames(pageable = Pageable.ofSize(100)) } returns firstnames.toList()

        mockMvc.perform(get("/api/v1/firstnames/random"))
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.*", hasSize<Firstname>(2)))
    }

    @Test
    @WithMockUser(username = "myUser", roles = ["USER"])
    fun givenExistingFirstnames_whenFindFirstnameWithPaginationAndSize_thenReturnsFirstnameJsonWithStatus200() {
        every { firstnameService.findFirstnames(pageable = Pageable.ofSize(20)) } returns firstnames
        every { firstnameService.findFirstnames(pageable = Pageable.unpaged()) } returns firstnames

        mockMvc.perform(get("/api/v1/firstnames"))
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.*", hasSize<Firstname>(2)))
    }

    @Test
    @WithMockUser(username = "myUser", roles = ["USER"])
    fun givenExistingFirstnames_whenFindFirstnameWithPaginationAndSizeOf1_thenReturnsFirstnameJsonWithStatus200() {
        every { firstnameService.findFirstnames(pageable = Pageable.ofSize(1)) } returns firstnamesOf1Element
        every { firstnameService.findFirstnames(pageable = Pageable.unpaged()) } returns firstnamesOf1Element

        mockMvc.perform(get("/api/v1/firstnames?size=1"))
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.*", hasSize<Firstname>(1)))
    }

    @Test
    @WithMockUser(username = "myUser", roles = ["USER"])
    fun givenExistingFirstnames_whenFindFirstnameWithPaginationAndSizeOf1AndPage1_thenReturnsFirstnameJsonWithStatus200() {
        every { firstnameService.findFirstnames(lang = "en", pageable = Pageable.ofSize(1).withPage(1)) } returns firstnamesOf1Element
        every { firstnameService.findFirstnames(pageable = Pageable.unpaged()) } returns firstnamesOf1Element


        mockMvc.perform(get("/api/v1/firstnames?size=1&page=1"))
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.*", hasSize<Firstname>(1)))
    }

    @Test
    @WithMockUser(username = "myUser", roles = ["USER"])
    fun givenExistingFirstnames_whenFindRandomFirstnameWithPaginationAndSizeOf3_thenReturnsFirstnameJsonWithStatus200() {
        every { firstnameService.findRandomFirstnames(pageable = Pageable.ofSize(3)) } returns firstnamesOf3Elements.toList()
        every { firstnameService.findRandomFirstnames(pageable = Pageable.unpaged()) } returns firstnamesOf3Elements.toList()

        mockMvc.perform(get("/api/v1/firstnames/random?size=3"))
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.*", hasSize<Firstname>(3)))
    }
}