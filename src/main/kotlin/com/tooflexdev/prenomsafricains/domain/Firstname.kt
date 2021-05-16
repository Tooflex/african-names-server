package com.tooflexdev.prenomsafricains.domain

import org.hibernate.annotations.Cache
import org.hibernate.annotations.CacheConcurrencyStrategy
import org.hibernate.annotations.ColumnTransformer
import javax.persistence.*

@Entity
@Table(name = "firstname")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
class Firstname(
    @Id var id: Long? = null,
    var firstname: String,
    @Enumerated(EnumType.STRING)
    @ColumnTransformer(read = "UPPER(gender)")
    var gender: Gender,
    @Column(length = 1024)
    var meaning: String?,
    @Column(length = 2048)
    var meaningMore: String? = "",
    var origins: String? = "",
    var regions: String? = "",
    var countries: String? = "",
    var nearingNames: String? = "",
    var celebrationDate: String? = "",
    var celebrities: String? = "",
    var soundURL: String? = ""
)
