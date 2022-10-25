package com.tooflexdev.prenomsafricains.domain

import com.opencsv.bean.CsvBindByPosition
import com.tooflexdev.prenomsafricains.annotation.GenderAnnotation
import org.hibernate.annotations.*
import org.hibernate.annotations.Cache
import java.time.LocalDateTime
import javax.persistence.*
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "firstname")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
class Firstname(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @CsvBindByPosition(position = 0)
    var id: Long = 0,

    @CsvBindByPosition(position = 5)
    var firstname: String = "No firstname",

    @Enumerated(EnumType.STRING)
    @GenderAnnotation(Gender.UNDEFINED)
    @ColumnTransformer(read = "UPPER(gender)")
    @CsvBindByPosition(position = 6)
    var gender: Gender = Gender.UNDEFINED,

    @Column(length = 2048)
    @CsvBindByPosition(position = 7)
    var meaning: String? = "No meaning",

    @Column(length = 4096)
    @CsvBindByPosition(position = 8)
    var meaningMore: String? = "",

    @CsvBindByPosition(position = 10)
    var origins: String? = "",

    @CsvBindByPosition(position = 11)
    var regions: String? = "",

    @CsvBindByPosition(position = 3)
    var countries: String? = "",

    @CsvBindByPosition(position = 9)
    var nearingNames: String? = "",

    @CsvBindByPosition(position = 1)
    var celebrationDate: String? = "",

    @CsvBindByPosition(position = 2)
    var celebrities: String? = "",

    @CsvBindByPosition(position = 13)
    var soundURL: String? = "",

    @Enumerated(EnumType.STRING)
    @ColumnTransformer(read = "UPPER(size)")
    @CsvBindByPosition(position = 12)
    var size: Size = Size.SHORT,

    @CreationTimestamp
    @CsvBindByPosition(position = 4)
    var createDateTime: LocalDateTime? = LocalDateTime.now(),

    @UpdateTimestamp
    @CsvBindByPosition(position = 14)
    var updateDateTime: LocalDateTime? = LocalDateTime.now()
) {

    @PrePersist
    fun onCreate(){
        size = determineSize(firstname)
    }

    @PreUpdate
    fun onUpdate(){
        size = determineSize(firstname)
    }

    fun determineSize(firstname: String): Size {
        if(firstname.length < 6) {
            return Size.SHORT
        }
        if(firstname.length > 10) {
            return Size.LONG
        }
        return Size.MEDIUM
    }
}
