/*
 * Copyright (c) 2021.
 * @Author Otourou Da Costa
 */

package com.tooflexdev.prenomsafricains.security

import com.tooflexdev.prenomsafricains.domain.User
import com.tooflexdev.prenomsafricains.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
class UserDetailsServiceImpl : UserDetailsService {
    @Autowired
    var userRepository: UserRepository? = null

    @Transactional
    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(username: String?): UserDetails? {
        val user: User? = userRepository!!.findByUsername(username)
            ?.orElseThrow {
                UsernameNotFoundException(
                    "User Not Found with username: $username"
                )
            }
        return user?.let { UserDetailsImpl.build(it) }
    }
}