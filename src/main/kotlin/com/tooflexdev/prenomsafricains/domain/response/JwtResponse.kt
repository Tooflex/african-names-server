/*
 * Copyright (c) 2021.
 * @Author Otourou Da Costa
 */

package com.tooflexdev.prenomsafricains.domain.response

class JwtResponse(
    var jwt: String,
    var id: Long,
    var username: String,
    var email: String,
    var roles: MutableList<String>
)
