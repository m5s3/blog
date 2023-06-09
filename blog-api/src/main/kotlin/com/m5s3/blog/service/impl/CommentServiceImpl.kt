package com.m5s3.blog.service.impl

import com.m5s3.blog.domain.BlogEntity
import com.m5s3.blog.domain.CommentEntity
import com.m5s3.blog.domain.MemberEntity
import com.m5s3.blog.repository.BlogRepository
import com.m5s3.blog.repository.CommentRepository
import com.m5s3.blog.repository.MemberRepository
import com.m5s3.blog.service.CommentService
import com.m5s3.blog.service.dto.CommentParamS
import com.m5s3.blog.service.dto.CommentResultS
import com.m5s3.blog.service.dto.toEntity
import com.m5s3.blog.service.exception.DataNotFoundException
import com.m5s3.blog.service.exception.DataNotFoundExceptionWhenDelete
import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CommentServiceImpl(
    private val commentRepository: CommentRepository,
    private val memberRepository: MemberRepository,
    private val blogRepository: BlogRepository
) : CommentService {
    @Transactional(readOnly = false)
    override fun createComment(param: CommentParamS): CommentResultS {
        try {
            val memberEntity: MemberEntity = memberRepository.getReferenceById(param.member.id!!)
            val blogEntity: BlogEntity = blogRepository.getReferenceById(param.blogId)
            val commentEntity: CommentEntity = commentRepository.save(param.toEntity(member = memberEntity, blog = blogEntity))
            return CommentResultS(commentEntity)
        } catch(ex: EntityNotFoundException) {
            throw DataNotFoundException("Not Found Error : (${ex.localizedMessage})")
        }
    }

    @Transactional(readOnly = false)
    override fun deleteCommentById(commentId: Long) {
        try {
            println("commentId : ${commentId}")
            commentRepository.deleteById(commentId)
        } catch(ex: IllegalArgumentException) {
            throw DataNotFoundExceptionWhenDelete("Not Found Comment Error(${commentId})")
        }
    }
}