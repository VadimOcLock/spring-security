package com.vadimoclock.spring.security.springsecurity.entity

import jakarta.persistence.*

@Entity
@Table(name = "users", schema = "securitydao")
class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0
    val username: String = ""
    val password: String = ""
    val email: String = ""

    @ManyToMany(cascade = [CascadeType.PERSIST, CascadeType.MERGE])
    @JoinTable(
        name = "user_roles",
        schema = "securitydao",
        joinColumns = [JoinColumn(name = "user_id")],
        inverseJoinColumns = [JoinColumn(name = "role_id")]
    )
    var roles: Collection<Role> = listOf()
}