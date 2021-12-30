package com.tooflexdev.prenomsafricains.domain

import org.hibernate.annotations.Cache
import org.hibernate.annotations.CacheConcurrencyStrategy
import javax.persistence.*

@Entity
@Table(name = "firstname_translation")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
class FirstnameTranslation (
    @Id var id: Long? = null,
    var meaningTranslation: String?,
    var originsTranslation: String?,
    @ManyToOne
    var language: Language,
    @ManyToOne
    var firstname: Firstname
)