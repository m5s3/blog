package com.m5s3.blog.service.dto

import com.m5s3.blog.controller.v1.dto.MemberResultC
import com.m5s3.blog.domain.BlogEntity
import com.m5s3.blog.domain.MemberEntity

data class BlogResultS(
    val id: Long,
    val title: String,
    val content: String,
    val member: MemberResultS,
) {
    companion object {
        operator fun invoke(blogEntity: BlogEntity) =
            with(blogEntity) {
                BlogResultS(
                    id = id!!,
                    title = title,
                    content = content,
                    member = member.toResultS()
                )
            }
    }
}

data class BlogParamS(
    val id: Long? = 0,
    val title: String? = null,
    val content: String? = null,
    val member: MemberParamS
)

fun BlogParamS.toEntity(member: MemberEntity) = BlogEntity(
    title = title!!,
    content = content!!,
    member = member
)