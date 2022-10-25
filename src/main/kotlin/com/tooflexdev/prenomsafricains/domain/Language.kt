package com.tooflexdev.prenomsafricains.domain

import org.hibernate.annotations.Cache
import org.hibernate.annotations.CacheConcurrencyStrategy
import javax.persistence.*

@Entity
@Table(name = "language")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
class Language (
    @Id
    var languageCode: String,
    var name: String,
    var nameInNativeLanguage: String
)