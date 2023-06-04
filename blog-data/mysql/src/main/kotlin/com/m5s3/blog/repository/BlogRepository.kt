package com.m5s3.blog.repository

import com.m5s3.blog.domain.BlogEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.CrudRepository

interface BlogRepository: JpaRepository<BlogEntity, Long> {
}