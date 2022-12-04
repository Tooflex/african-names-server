/*
 * Copyright (c) 2021.
 * @Author Otourou Da Costa
 */

package com.tooflexdev.prenomsafricains.service

import com.opencsv.bean.CsvToBean
import com.opencsv.bean.CsvToBeanBuilder
import com.tooflexdev.prenomsafricains.domain.Firstname
import com.tooflexdev.prenomsafricains.domain.FirstnameTranslation
import com.tooflexdev.prenomsafricains.dto.FirstnameTranslationDTO
import com.tooflexdev.prenomsafricains.dto.FirstnameTranslationMapper
import com.tooflexdev.prenomsafricains.exception.BadRequestException
import com.tooflexdev.prenomsafricains.exception.CsvImportException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

@Service
class CsvService {

    @Autowired
    lateinit var firstnameService: FirstnameService

    @Autowired
    lateinit var firstnameTranslationService: FirstnameTranslationService

    @Autowired
    lateinit var firstnameTranslationMapper: FirstnameTranslationMapper

    fun uploadCsvFile(file: MultipartFile): MutableIterable<Firstname> {
        throwIfFileEmpty(file)
        var fileReader: BufferedReader? = null

        try {
            fileReader = BufferedReader(InputStreamReader(file.inputStream))
            val csvToBean = createCSVToBean(fileReader)

            val firstnames = csvToBean.parse()

            return firstnameService.saveAll(firstnames)
        } catch (ex: Exception) {
            throw CsvImportException("Error during csv import")
        } finally {
            closeFileReader(fileReader)
        }
    }

    fun uploadFirstnameTranslationCsvFile(file: MultipartFile): MutableIterable<FirstnameTranslation> {
        throwIfFileEmpty(file)
        var fileReader: BufferedReader? = null

        try {
            fileReader = BufferedReader(InputStreamReader(file.inputStream))
            val csvToBean = createFirstnameTranslationCSVToBean(fileReader)

            val firstnamesDTO = csvToBean.parse()

            val firstnames = firstnamesDTO.map { firstnameTranslationMapper.toFirstnameTranslation(it) }

            return firstnameTranslationService.saveAll(firstnames)
        } catch (ex: Exception) {
            throw CsvImportException("Error during csv import")
        } finally {
            closeFileReader(fileReader)
        }
    }

    private fun throwIfFileEmpty(file: MultipartFile) {
        if (file.isEmpty)
            throw BadRequestException("Empty file")
    }

    private fun createCSVToBean(fileReader: BufferedReader?): CsvToBean<Firstname> =
        CsvToBeanBuilder<Firstname>(fileReader)
            .withType(Firstname::class.java)
            .withSkipLines(1)
            .withIgnoreLeadingWhiteSpace(true)
            .withSeparator(';')
            .build()

    private fun createFirstnameTranslationCSVToBean(fileReader: BufferedReader?): CsvToBean<FirstnameTranslationDTO> =
        CsvToBeanBuilder<FirstnameTranslationDTO>(fileReader)
            .withType(FirstnameTranslationDTO::class.java)
            .withSkipLines(1)
            .withIgnoreLeadingWhiteSpace(true)
            .withSeparator(';')
            .build()

    private fun closeFileReader(fileReader: BufferedReader?) {
        try {
            fileReader!!.close()
        } catch (ex: IOException) {
            throw CsvImportException("Error during csv import")
        }
    }
}