package com.m5s3.blog.config

import org.springframework.boot.autoconfigure.security.servlet.PathRequest
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class SecurityConfig {
    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http.csrf().disable()
        http.headers().frameOptions().sameOrigin().disable()
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        http.authorizeHttpRequests().anyRequest().permitAll()
        return http.build()
//        http.authorizeHttpRequests()
//            .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
//            .requestMatchers(HttpMethod.GET).permitAll()
//            .and()
//            .formLogin()
//            .and()
//            .logout()
//            .logoutSuccessUrl("/")
//        return http.build()
    }

//    @Bean
//    fun userDetailService(userDetailsService: UserDetailsService): UserDetailsService =
//         UserDetailsService { userId: String ->
//             userDetailsService
//                .loadUserByUsername(userId)
//        }
}