package com.m5s3.blog.domain

import com.m5s3.blog.domain.base.BaseEntity
import jakarta.persistence.*

@Entity
@Table(name = "blog")
class BlogEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = 0,

    @Column(nullable = false)
    var title: String,

    @Column(length = 10000, nullable = false)
    var content: String,

    @ManyToOne(optional = false)
    @JoinColumn(name = "userId")
    val member: MemberEntity
) : BaseEntity()