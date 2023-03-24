package com.vadimoclock.spring.security.springsecurity.service

import com.vadimoclock.spring.security.springsecurity.entity.Role
import com.vadimoclock.spring.security.springsecurity.entity.User
import com.vadimoclock.spring.security.springsecurity.repository.UserRepository
import jakarta.transaction.Transactional
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository
): UserDetailsService {

    fun findByUsername(username: String): User =
        userRepository.findByUsername(username)

    @Transactional
    override fun loadUserByUsername(username: String): UserDetails {

        val user = userRepository.findByUsername(username)
            ?: throw UsernameNotFoundException(username)

        return org.springframework.security.core.userdetails.User(
            user.username,
            user.password,
            mapRolesToAuthorities(user.roles)
        )

    }

    private fun mapRolesToAuthorities(roles: Collection<Role>): Collection<GrantedAuthority> =
        roles.map { SimpleGrantedAuthority(it.name) }.toList()

}