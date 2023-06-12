package com.m5s3.blog.security.dto

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

data class CustomUserDetail(
    private val username: String,
    private val password: String,
    private val authorities: Collection<GrantedAuthority>,
    private val isEnabled: Boolean = true,
    private val isAccountNonExpired: Boolean = true,
    private val isAccountNonLocked: Boolean = true,
    private val isCredentialsNonExpired: Boolean = true
) : UserDetails {
    override fun getUsername() = username

    override fun getPassword() = password

    override fun getAuthorities() = authorities

    override fun isEnabled() = isEnabled

    override fun isAccountNonExpired() = isAccountNonExpired

    override fun isAccountNonLocked() = isAccountNonLocked

    override fun isCredentialsNonExpired() = isCredentialsNonExpired
}