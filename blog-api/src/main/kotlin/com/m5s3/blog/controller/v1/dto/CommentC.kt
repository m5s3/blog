package com.m5s3.blog.controller.v1.dto

import com.m5s3.blog.service.dto.BlogResultS
import com.m5s3.blog.service.dto.CommentParamS
import com.m5s3.blog.service.dto.CommentResultS

data class CommentParamC(
    val blogId: Long,
    val content: String? = null,
    val parentCommentId: Long? = null,
    val commentId: Long? = null
)

fun CommentParamC.toS(member: MemberParamC) = CommentParamS(
    content = content,
    blogId = blogId,
    commentId = commentId,
    parentCommentId = parentCommentId,
    member = member.toS(),
)


data class CommentResultC(
    val id: Long,
    val content: String,
    val parentCommentId: Long?,
    val member: MemberResultC,
) {
    companion object {
        operator fun invoke(commentResultS: CommentResultS) =
            with(commentResultS) {
                CommentResultC(
                    id = id,
                    content = content,
                    member = MemberResultC(member),
                    parentCommentId = parentCommentId
                )
            }
    }
}