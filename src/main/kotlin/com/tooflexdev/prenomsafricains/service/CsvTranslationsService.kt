/*
 * Copyright (c) 2021.
 * @Author Otourou Da Costa
 */

package com.tooflexdev.prenomsafricains.service

import com.opencsv.bean.CsvToBean
import com.opencsv.bean.CsvToBeanBuilder
import com.tooflexdev.prenomsafricains.domain.Firstname
import com.tooflexdev.prenomsafricains.domain.FirstnameTranslation
import com.tooflexdev.prenomsafricains.exception.BadRequestException
import com.tooflexdev.prenomsafricains.exception.CsvImportException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

@Service
class CsvTranslationService {

    @Autowired
    lateinit var firstnameTranslationService: FirstnameTranslationService

    fun uploadFirstnameTranslationCsvFile(file: MultipartFile): List<FirstnameTranslation> {
        throwIfFileEmpty(file)
        var fileReader: BufferedReader? = null

        try {
            fileReader = BufferedReader(InputStreamReader(file.inputStream))
            val csvToBean = createCSVToBean(fileReader)

            val translations = csvToBean.parse()

            return firstnameTranslationService.saveAll(translations)
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

    private fun createCSVToBean(fileReader: BufferedReader?): CsvToBean<FirstnameTranslation> =
        CsvToBeanBuilder<FirstnameTranslation>(fileReader)
            .withType(FirstnameTranslation::class.java)
            .withSkipLines(1)
            .withIgnoreLeadingWhiteSpace(true)
            .build()

    private fun closeFileReader(fileReader: BufferedReader?) {
        try {
            fileReader!!.close()
        } catch (ex: IOException) {
            throw CsvImportException("Error during csv import")
        }
    }
}