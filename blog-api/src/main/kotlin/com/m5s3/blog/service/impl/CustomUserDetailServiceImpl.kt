package com.m5s3.blog.service.impl

import com.m5s3.blog.repository.MemberRepository
import com.m5s3.blog.security.dto.CustomUserDetail
import com.m5s3.blog.security.enums.RoleType
import com.m5s3.blog.service.exception.DataNotFoundException
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service("UserDetailService")
class CustomUserDetailServiceImpl(
    val memberRepository: MemberRepository
) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {
        val member = memberRepository.findByUserId(username) ?: throw DataNotFoundException("Not Found Error(${username})")
        val roleTypes: RoleType = RoleType.USER
        val authorities = SimpleGrantedAuthority(roleTypes.name)
        return CustomUserDetail(
            username = member.userId,
            password = member.password,
            authorities = setOf(authorities)
        )
    }
}