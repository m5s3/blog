package com.m5s3.blog.domain

import com.m5s3.blog.domain.base.BaseEntity
import jakarta.persistence.*

@Entity
@Table
class MemberEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(length = 50, nullable = false)
    val userId: String,

    @Column(length = 100, nullable = false)
    val password: String,

    @Column(length = 100)
    val email: String,

    @Column(length = 50)
    val nickName: String,

    @Column
    val memo: String
) : BaseEntity()