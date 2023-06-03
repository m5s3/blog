package com.m5s3.blog.config

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
@EnableAutoConfiguration(exclude = [UserDetailsServiceAutoConfiguration::class])
class SecurityConfig {
    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain? {
        http.csrf().disable()
        http.headers().frameOptions().sameOrigin().disable()
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        http.authorizeHttpRequests().anyRequest().permitAll()
        return http.build()
    }
}