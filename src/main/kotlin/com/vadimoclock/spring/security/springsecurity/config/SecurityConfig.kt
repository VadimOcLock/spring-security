package com.vadimoclock.spring.security.springsecurity.config

import com.vadimoclock.spring.security.springsecurity.service.UserService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.provisioning.JdbcUserDetailsManager
import org.springframework.security.web.SecurityFilterChain
import javax.sql.DataSource

@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val userService: UserService
) {

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http.authorizeHttpRequests()
            .requestMatchers("/", "/home").permitAll()
            .requestMatchers("/authenticated/**").authenticated()
            .requestMatchers("/admin_page/**").hasRole("ADMIN")
            .requestMatchers("/read_profile/**").hasAuthority("READ_PROFILE")
            .and()
            .formLogin()
            .and()
            .logout().logoutSuccessUrl("/")
        return http.build()
    }

//    @Bean
//    fun users(): UserDetailsService {
//        val user: UserDetails = User.builder()
//            .username("user")
//            .password("{bcrypt}$2a$12\$qwAiFCojVhKOoP7WxY0oyO2/2qvKayrbj5PhhIkOuMssXEDyYn.Oa")
//            .roles("USER")
//            .build()
//
//        val admin: UserDetails = User.builder()
//            .username("admin")
//            .password("{bcrypt}$2a$12\$qwAiFCojVhKOoP7WxY0oyO2/2qvKayrbj5PhhIkOuMssXEDyYn.Oa")
//            .roles("USER", "ADMIN")
//            .build()
//
//        return InMemoryUserDetailsManager(user, admin)
//    }

//    @Bean
//    fun users(dataSource: DataSource): JdbcUserDetailsManager {
//
////        val user: UserDetails = User.builder()
////            .username("user")
////            .password("{bcrypt}$2a$12\$qwAiFCojVhKOoP7WxY0oyO2/2qvKayrbj5PhhIkOuMssXEDyYn.Oa")
////            .roles("USER")
////            .build()
////
////        val admin: UserDetails = User.builder()
////            .username("admin")
////            .password("{bcrypt}$2a$12\$qwAiFCojVhKOoP7WxY0oyO2/2qvKayrbj5PhhIkOuMssXEDyYn.Oa")
////            .roles("ADMIN", "USER")
////            .build()
//
//        val jdbcUserDetailsManager = JdbcUserDetailsManager(dataSource)
//
////        if (jdbcUserDetailsManager.userExists(user.username))
////            jdbcUserDetailsManager.deleteUser(user.username)
////
////        if (jdbcUserDetailsManager.userExists(admin.username))
////            jdbcUserDetailsManager.deleteUser(admin.username)
////
////        jdbcUserDetailsManager.createUser(user)
////        jdbcUserDetailsManager.createUser(admin)
//
//        return jdbcUserDetailsManager
//    }

    @Bean
    fun passwordEncoder(): PasswordEncoder =
        BCryptPasswordEncoder()

    @Bean
    fun daoAuthenticationProvider(): DaoAuthenticationProvider {
        val authenticationProvider = DaoAuthenticationProvider()
        authenticationProvider.setPasswordEncoder(passwordEncoder())
        authenticationProvider.setUserDetailsService(userService)

        return authenticationProvider
    }

}