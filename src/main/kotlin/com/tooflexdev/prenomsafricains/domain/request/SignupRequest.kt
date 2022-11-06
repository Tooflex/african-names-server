/*
 * Copyright (c) 2021.
 * @Author Otourou Da Costa
 */

package com.tooflexdev.prenomsafricains.domain.request

class SignupRequest (
    var username: String,
    var email: String,
    var password: String,
    var roles: Set<String>
)