/*
 * Copyright (c) 2022.
 * @Author Otourou Da Costa
 */

package com.tooflexdev.prenomsafricains.dto

import com.opencsv.bean.CsvBindByPosition
import com.opencsv.bean.CsvIgnore
import org.springframework.stereotype.Component

@Component
class FirstnameTranslationDTO(
    @CsvIgnore
    var id: Long = 0,
    @CsvBindByPosition(position = 1)
    var meaningTranslation: String? = "No meaning",
    @CsvBindByPosition(position = 2)
    var originsTranslation: String? = "No origin",
    @CsvBindByPosition(position = 3)
    var languageCode: String? = "en",
    @CsvBindByPosition(position = 4)
    var firstnameId: Long? = 0
) {

}
