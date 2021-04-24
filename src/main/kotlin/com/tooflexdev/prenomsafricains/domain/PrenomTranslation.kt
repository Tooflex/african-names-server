package com.tooflexdev.prenomsafricains.domain

import org.hibernate.annotations.Cache
import org.hibernate.annotations.CacheConcurrencyStrategy
import org.hibernate.annotations.ColumnTransformer
import javax.persistence.*

@Entity
@Table(name = "prenom_translation")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
class PrenomTranslation (
    @Id var id: Long? = null,
    var meaningTranslation: String?,
    var originsTranslation: String?,
    @ManyToOne
    var language: Language,
    @ManyToOne
    var product: Prenom
)