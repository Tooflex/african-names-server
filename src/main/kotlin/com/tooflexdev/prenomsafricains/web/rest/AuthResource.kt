/*
 * Copyright (c) 2021.
 * @Author Otourou Da Costa
 */

package com.tooflexdev.prenomsafricains.web.rest

import com.tooflexdev.prenomsafricains.domain.*
import com.tooflexdev.prenomsafricains.repository.RoleRepository
import com.tooflexdev.prenomsafricains.repository.UserRepository
import com.tooflexdev.prenomsafricains.security.JwtUtils
import com.tooflexdev.prenomsafricains.security.UserDetailsImpl
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.*
import java.util.function.Consumer
import java.util.stream.Collectors
import javax.validation.Valid

@RestController
@CrossOrigin(origins = ["localhost:4200", "http://localhost", "http://0.0.0.0"], maxAge = 3600)
@RequestMapping("/api/v1/auth")
class AuthResource {
    @Autowired
    var authenticationManager: AuthenticationManager? = null

    @Autowired
    var userRepository: UserRepository? = null

    @Autowired
    var roleRepository: RoleRepository? = null

    @Autowired
    var encoder: PasswordEncoder? = null

    @Autowired
    var jwtUtils: JwtUtils? = null

    @Operation(summary = "Authenticate user")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "User authenticated",
            content = [Content(mediaType = "application/json",
                schema = Schema(implementation = JwtResponse::class))]),
        ApiResponse(responseCode = "400", description = "Bad request", content = [Content()]),
        ApiResponse(responseCode = "404", description = "User not found", content = [Content()])]
    )
    @PostMapping("/login")
    fun authenticateUser(@RequestBody loginRequest: @Valid LoginRequest?): ResponseEntity<*> {
        val authentication = authenticationManager!!.authenticate(
            UsernamePasswordAuthenticationToken(loginRequest?.username ?: "", loginRequest?.password ?: "")
        )
        SecurityContextHolder.getContext().authentication = authentication
        val jwt = jwtUtils!!.generateJwtToken(authentication)
        val userDetails = authentication.principal as UserDetailsImpl
        val roles = userDetails.authorities.stream()
            .map { item: GrantedAuthority -> item.authority }
            .collect(Collectors.toList())
        return ResponseEntity.ok<Any>(
            JwtResponse(
                jwt,
                userDetails.id,
                userDetails.username,
                userDetails.email,
                roles
            )
        )
    }

    @PostMapping("/signup")
    fun registerUser(@RequestBody signUpRequest: @Valid SignupRequest?): ResponseEntity<*> {
        if (userRepository!!.existsByUsername(signUpRequest?.username)!!) {
            return ResponseEntity
                .badRequest()
                .body<Any>(MessageResponse("Error: Username is already taken!"))
        }
        if (userRepository!!.existsByEmail(signUpRequest?.email)!!) {
            return ResponseEntity
                .badRequest()
                .body<Any>(MessageResponse("Error: Email is already in use!"))
        }

        // Create new user's account
        val user = User(
            signUpRequest?.username,
            signUpRequest?.email,
            encoder!!.encode(signUpRequest?.password)
        )
        val strRoles: Set<String> = signUpRequest!!.roles
        val roles: MutableSet<Role> = HashSet()
        strRoles.forEach(Consumer { role: String? ->
            when (role) {
                "admin" -> {
                    val adminRole: Role? = roleRepository!!.findByName(ERole.ROLE_ADMIN)
                        ?.orElseThrow {
                            RuntimeException(
                                "Error: Role is not found."
                            )
                        }
                    if (adminRole != null) {
                        roles.add(adminRole)
                    }
                }
                "mod" -> {
                    val modRole: Role? = roleRepository!!.findByName(ERole.ROLE_MODERATOR)
                        ?.orElseThrow {
                            RuntimeException(
                                "Error: Role is not found."
                            )
                        }
                    if (modRole != null) {
                        roles.add(modRole)
                    }
                }
                else -> {
                    val userRole: Role? = roleRepository!!.findByName(ERole.ROLE_USER)
                        ?.orElseThrow {
                            RuntimeException(
                                "Error: Role is not found."
                            )
                        }
                    if (userRole != null) {
                        roles.add(userRole)
                    }
                }
            }
        })
        user.roles = roles
        userRepository!!.save(user)
        return ResponseEntity.ok<Any>(MessageResponse("User registered successfully!"))
    }
}