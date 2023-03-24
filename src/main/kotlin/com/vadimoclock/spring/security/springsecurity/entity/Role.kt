package com.vadimoclock.spring.security.springsecurity.entity

import jakarta.persistence.*


@Entity
@Table(name = "roles", schema = "securitydao")
class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0
    val name: String = ""

}