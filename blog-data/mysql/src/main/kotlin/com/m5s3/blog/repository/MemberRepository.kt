package com.m5s3.blog.repository

import com.m5s3.blog.domain.MemberEntity
import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository: JpaRepository<MemberEntity, Long> {
}