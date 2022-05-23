/*
 * Copyright (c) 2021.
 * @Author Otourou Da Costa
 */

package com.tooflexdev.prenomsafricains.security

import com.tooflexdev.prenomsafricains.domain.User
import com.tooflexdev.prenomsafricains.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.function.Supplier


@Service
class UserDetailsServiceImpl : UserDetailsService, org.springframework.security.core.userdetails.UserDetailsService {
    @Autowired
    var userRepository: UserRepository? = null

    @Transactional
    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(username: String?): UserDetails? {
        val user: User? = userRepository!!.findByUsername(username)
            ?.orElseThrow(Supplier {
                UsernameNotFoundException(
                    "User Not Found with username: $username"
                )
            })
        return user?.let { UserDetailsImpl.build(it) }
    }
}