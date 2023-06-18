package com.m5s3.blog.repository

import com.m5s3.blog.domain.CommentEntity
import org.springframework.data.jpa.repository.JpaRepository

interface CommentRepository: JpaRepository<CommentEntity, Long> {

}