package com.m5s3.blog.repository

import com.m5s3.blog.domain.MemberEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.CrudRepository

interface MemberRepository: CrudRepository<MemberEntity, Long> {
    fun findByUserId(userId: String) : MemberEntity?
    fun findAll(pageable: Pageable) : Page<MemberEntity>
}