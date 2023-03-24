package com.vadimoclock.spring.security.springsecurity.repository

import com.vadimoclock.spring.security.springsecurity.entity.Role
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RoleRepository: JpaRepository<Role, Long> {

}