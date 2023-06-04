package com.m5s3.blog.controller.v1.dto

import com.m5s3.blog.service.dto.BlogParamS
import com.m5s3.blog.service.dto.BlogResultS
import com.m5s3.blog.service.dto.MemberResultS


data class BlogParamC(
    val title: String? = null,
    val content: String? = null
)

fun BlogParamC.toS(member: MemberParamC) = BlogParamS(
    title = title,
    content = content,
    member = member.toS()
)

data class BlogResultC(
    val id: Long,
    val title: String,
    val content: String,
    val member: MemberResultC
) {
    companion object {
        operator fun invoke(blogResultS: BlogResultS) =
            with(blogResultS) {
                BlogResultC(
                    id = id,
                    title = title,
                    content = content,
                    member = MemberResultC(member)
                )
            }
    }
}