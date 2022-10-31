package com.tooflexdev.prenomsafricains.domain

import com.opencsv.bean.CsvBindByPosition
import org.hibernate.annotations.Cache
import org.hibernate.annotations.CacheConcurrencyStrategy
import javax.persistence.*

@Entity
@Table(name = "firstname_translation")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
class FirstnameTranslation (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @CsvBindByPosition(position = 0)
    var id: Long? = null,
    @Column(length = 4096)
    @CsvBindByPosition(position = 1)
    var meaningTranslation: String?,
    @CsvBindByPosition(position = 2)
    var originsTranslation: String?,
    @ManyToOne
    @CsvBindByPosition(position = 3)
    var language: Language,
    @ManyToOne
    @CsvBindByPosition(position = 4)
    var firstname: Firstname
)