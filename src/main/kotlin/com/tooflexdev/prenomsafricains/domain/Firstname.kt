package com.tooflexdev.prenomsafricains.domain

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
    var soundURL: String? = "",
    @Enumerated(EnumType.STRING)
    @ColumnTransformer(read = "UPPER(size)")
    var size: Size,
    @CreationTimestamp
    var createDateTime: LocalDateTime?,
    @UpdateTimestamp
    var updateDateTime: LocalDateTime?
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
