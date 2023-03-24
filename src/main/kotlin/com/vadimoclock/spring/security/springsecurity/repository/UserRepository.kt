package com.vadimoclock.spring.security.springsecurity.repository

import org.springframework.data.jpa.repository.JpaRepository
import com.vadimoclock.spring.security.springsecurity.entity.User
import org.springframework.stereotype.Repository

@Repository
interface UserRepository: JpaRepository<User, Long> {

    fun findByUsername(username: String): User

}