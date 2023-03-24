package com.vadimoclock.spring.security.springsecurity.controller

import com.vadimoclock.spring.security.springsecurity.service.UserService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.security.Principal

@RestController
class MainController(
    private val userService: UserService
) {

    @GetMapping("/")
    fun homePage(): String =
        "Home"

    @GetMapping("/authenticated")
    fun pageForAuthUsers(principal: Principal): String {
        val user = userService.findByUsername(principal.name)

        return "Secured part of web service: " +
                "Username: ${user.username} " +
                "Email: ${user.email}"
    }

    @GetMapping("/admin_page")
    fun pageOnlyForAdmins(): String =
        "Admin page"

    @GetMapping("/read_profile")
    fun pageForReadProfile(): String =
        "read profile"
}