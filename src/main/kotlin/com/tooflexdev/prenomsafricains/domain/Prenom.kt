package com.tooflexdev.prenomsafricains.domain

import org.hibernate.annotations.Cache
import org.hibernate.annotations.CacheConcurrencyStrategy
import org.hibernate.annotations.ColumnTransformer
import javax.persistence.*

@Entity
@Table(name = "prenom")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
class Prenom(
    @Id var id: Long? = null,
    var firstname: String,
    @Enumerated(EnumType.STRING)
    @ColumnTransformer(read = "UPPER(gender)")
    var gender: Gender,
    var isFavorite: Boolean = false,
    var meaning: String?,
    var origins: String?,
    var soundURL: String?
)
