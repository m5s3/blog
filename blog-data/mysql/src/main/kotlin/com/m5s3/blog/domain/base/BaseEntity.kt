package com.m5s3.blog.domain.base

import jakarta.persistence.Column
import jakarta.persistence.EntityListeners
import jakarta.persistence.MappedSuperclass
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDateTime

@MappedSuperclass
@EntityListeners(value = [AuditingEntityListener::class])
abstract class BaseEntity {
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false, columnDefinition = "DATE")
    var createdDate: LocalDateTime? = null

    @CreatedBy
    @Column(name = "created_by", nullable = false, updatable = false, length = 50)
    var createdBy: String? = null

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @LastModifiedDate
    @Column(name = "updated_at", nullable = false, columnDefinition = "DATE")
    var updatedDate: LocalDateTime? = null

    @LastModifiedBy
    @Column(name = "updated_by", nullable = false, length = 50)
    var updatedBy: String? = null
}