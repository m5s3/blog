package com.m5s3.blog.service.dto

import com.m5s3.blog.domain.BlogEntity
import com.m5s3.blog.domain.CommentEntity
import com.m5s3.blog.domain.MemberEntity

data class CommentParamS(
    val id:Long? = 0,
    val content: String? = null,
    val member: MemberParamS,
    val blogId: Long,
    val commentId: Long?,
    val parentCommentId: Long? = null,
)

fun CommentParamS.toEntity(member: MemberEntity, blog: BlogEntity) = CommentEntity(
    blogEntity = blog,
    memberEntity = member,
    parentCommentId = parentCommentId,
    content = content!!
)

data class CommentResultS(
    val id:Long,
    val content: String,
    val member: MemberResultS,
    val blog: BlogResultS,
    val parentCommentId: Long?,
) {
    companion object {
        operator fun invoke(commentEntity: CommentEntity) =
            with(commentEntity) {
                CommentResultS(
                    id = id!!,
                    content = content,
                    blog = BlogResultS(blogEntity),
                    member = MemberResultS(memberEntity),
                    parentCommentId = parentCommentId,
                )
            }
    }
}