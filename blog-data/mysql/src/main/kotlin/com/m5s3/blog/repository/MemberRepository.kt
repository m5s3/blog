package com.m5s3.blog.repository

import com.m5s3.blog.domain.MemberEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository: JpaRepository<MemberEntity, Long> {
    fun findByUserId(userId: String) : MemberEntity?
    override fun findAll(pageable: Pageable) : Page<MemberEntity>
}