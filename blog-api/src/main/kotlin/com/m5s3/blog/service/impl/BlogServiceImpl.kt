package com.m5s3.blog.service.impl

import com.m5s3.blog.domain.BlogEntity
import com.m5s3.blog.domain.MemberEntity
import com.m5s3.blog.repository.BlogRepository
import com.m5s3.blog.repository.MemberRepository
import com.m5s3.blog.service.BlogService
import com.m5s3.blog.service.dto.BlogParamS
import com.m5s3.blog.service.dto.BlogResultS
import com.m5s3.blog.service.dto.toEntity
import com.m5s3.blog.service.exception.DataNotFoundException
import com.m5s3.blog.service.exception.DataNotFoundExceptionWhenDelete
import com.m5s3.blog.service.exception.DataNotFoundExceptionWhenModify
import jakarta.persistence.EntityNotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
class BlogServiceImpl(
    private val blogRepository: BlogRepository,
    private val memberRepository: MemberRepository
) : BlogService {

    @Transactional(readOnly = true)
    override fun getBlogById(id: Long): BlogResultS {
        val blogEntity: BlogEntity =
            blogRepository.findByIdOrNull(id)
                ?: throw DataNotFoundException("Not Found Error(${id})")

        return BlogResultS(blogEntity)
    }

    @Transactional(readOnly = false)
    override fun createBlog(param: BlogParamS): BlogResultS {
        val memberEntity: MemberEntity? = memberRepository.getReferenceById(param.member.id!!)
        if (memberEntity === null) {
            throw DataNotFoundException("Not Found Member Error(${param.member.id})")
        }

        val blogEntity: BlogEntity? = blogRepository.save(param.toEntity(memberEntity))
        if (blogEntity === null) {
            throw DataNotFoundException("Not Found Blog Error(${param.id})")
        }

        return BlogResultS(blogEntity)
    }

    @Transactional(readOnly = false)
    override fun modifyBlogById(blogId: Long, param: BlogParamS): BlogResultS {
        try {
            val memberEntity = memberRepository.getReferenceById(param.member.id!!)
            val blogEntity = blogRepository.getReferenceById(blogId)

            if (blogEntity.member == memberEntity) {
                if (param.title !== null) {
                    blogEntity.title = param.title
                }
                if (param.content !== null) {
                    blogEntity.content = param.content
                }
            }
            return BlogResultS(blogEntity)
        } catch(ex: EntityNotFoundException) {
            throw DataNotFoundExceptionWhenModify("Not Found Blog Error(${param.id})")
        }
    }

    @Transactional(readOnly = false)
    override fun deleteBlogById(blogId: Long) {
        try {
            blogRepository.deleteById(blogId)
        } catch(ex: IllegalArgumentException) {
            throw DataNotFoundExceptionWhenDelete("Not Found Blog Error(${blogId})")
        }
    }
}