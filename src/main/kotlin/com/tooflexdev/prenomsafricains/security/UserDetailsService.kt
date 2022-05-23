/*
 * Copyright (c) 2021.
 * @Author Otourou Da Costa
 */

package com.tooflexdev.prenomsafricains.security

import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException


interface UserDetailsService {
    @Throws(UsernameNotFoundException::class)
    fun loadUserByUsername(username: String?): UserDetails?
}