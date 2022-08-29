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
import io.mockk.every
import org.hamcrest.CoreMatchers.containsString
import org.hamcrest.collection.IsCollectionWithSize.hasSize
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
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

    private lateinit var firstname1: Firstname
    private lateinit var firstname2: Firstname
    private lateinit var firstnames: List<Firstname>
    private lateinit var firstName1Json: String

    @BeforeEach
    fun init() {

        firstname1 = Firstname(
            firstname = "Amadou",
            gender = Gender.MALE,
            meaning = "",
            size = Size.MEDIUM)

        firstname2 = Firstname(
            firstname = "Fatou",
            gender = Gender.FEMALE,
            meaning = "",
            size = Size.SHORT)

        firstnames = arrayListOf(firstname1, firstname2)

        val objectMapper: ObjectMapper = JsonMapper.builder()
            .findAndAddModules()
            .build()
        firstName1Json = objectMapper.writeValueAsString(firstname1)
    }

    @Test
    @WithMockUser(username = "myUser", roles = ["USER"])
    fun givenNoExistingFirstnames_whenGetRequest_thenReturnsFirstnameJsonWithStatus404() {
        every { firstnameService.findFirstnames() } returns emptyList()

        mockMvc.perform(get("/api/v1/firstnames"))
            .andExpect(status().isNotFound)
            .andExpect(content().string(containsString("No firstname found")))
    }

    @Test
    fun givenExistingFirstnamesAndUnauthorizedUser_whenGetRequest_thenReturnsFirstnameJsonWithStatus401() {
        every { firstnameService.findFirstnames() } returns firstnames

        mockMvc.perform(get("/api/v1/firstnames"))
            .andExpect(status().isUnauthorized)
    }

    @Test
    @WithMockUser(username = "myUser", roles = ["USER"])
    fun givenExistingFirstnames_whenGetRequest_thenReturnsFirstnameJsonWithStatus200() {
        every { firstnameService.findFirstnames() } returns firstnames

        mockMvc.perform(get("/api/v1/firstnames"))
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.*", hasSize<Firstname>(2)))
            .andExpect(jsonPath("$[0].firstname").value("Amadou"))
            .andExpect(jsonPath("$[1].firstname").value("Fatou"))
    }

    @Test
    @WithMockUser(username = "myUser", roles = ["USER"])
    fun givenExistingFirstnames_whenFindPrenomsAlea_thenReturnsFirstnameJsonWithStatus200() {
        every { firstnameService.findPrenomsAlea() } returns firstnames

        mockMvc.perform(get("/api/v1/firstnames/random"))
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.*", hasSize<Firstname>(2)))
    }

//    @Test
//    //@WithMockUser(username = "myUser", roles = ["USER"])
//    fun givenFirstnameInfoAndUserRole_whenPostRequest_withCreateFirstname_thenReturnsCreatedFirstnameWithStatus403() {
//        every { firstnameService.createFirstname(firstname1) } returns firstname1
//
//        mockMvc.perform(post("/api/v1/firstnames/")
//            .with(jwt()
//                .authorities(SimpleGrantedAuthority("USER")))
//        .contentType(MediaType.APPLICATION_JSON)
//            .content(firstName1Json)
//            .accept(MediaType.APPLICATION_JSON))
//            .andExpect(status().isForbidden)
//    }
//
//   @Test
//   //@WithMockUser(username = "myAdmin", roles = ["ADMIN"])
//   fun givenFirstnameInfoAndAdminRole_whenPostRequest_withCreateFirstname_thenReturnsCreatedFirstnameWithStatus201() {
//       every { firstnameService.createFirstname(firstname1) } returns firstname1
//
//       mockMvc.perform(post("/api/v1/firstnames/")
//           .with(jwt()
//               .authorities(SimpleGrantedAuthority("ADMIN")))
//           .contentType(MediaType.APPLICATION_JSON)
//               .content(firstName1Json)
//           .characterEncoding("utf-8")
//           .accept(MediaType.APPLICATION_JSON))
//              .andExpect(status().isCreated)
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//   }
}