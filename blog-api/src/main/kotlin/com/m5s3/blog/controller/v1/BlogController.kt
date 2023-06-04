package com.m5s3.blog.controller.v1

import com.m5s3.blog.controller.v1.dto.BlogParamC
import com.m5s3.blog.controller.v1.dto.BlogResultC
import com.m5s3.blog.controller.v1.dto.MemberParamC
import com.m5s3.blog.controller.v1.dto.toS
import com.m5s3.blog.service.BlogService
import com.m5s3.blog.service.exception.DataNotFoundExceptionWhenDelete
import com.m5s3.blog.service.exception.DataNotFoundExceptionWhenModify
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/api/v1/blog")
class BlogController(
    private val blogService: BlogService,
) {
    @GetMapping("/{id}")
    fun getBlog(
        @PathVariable id:Long
    ): BlogResultC {
        return BlogResultC(blogService.getBlogById(id))
    }

    @PostMapping
    fun createBlog(
        @RequestBody request: BlogParamC
    ): BlogResultC {
        //val member: MemberResultS = memberService.getMemberById(1L)
        val member = MemberParamC(1,"test", "test", "test@test.com", "test", "test")
        return BlogResultC(blogService.createBlog(request.toS(member)))
    }

    @PutMapping("{id}")
    fun modifyBlog(
        @PathVariable id: Long,
        @RequestBody request: BlogParamC
    ): BlogResultC {
        try {
            val member = MemberParamC(1,"test", "test", "test@test.com", "test", "test")
            return BlogResultC(blogService.modifyBlogById(id, request.toS(member)))
        } catch(e: DataNotFoundExceptionWhenModify) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, e.message, e)
        }
    }

    @DeleteMapping("{id}")
    fun deleteBlog(
        @PathVariable id: Long
    ) {
        try {
            blogService.deleteBlogById(id)
        } catch(e: DataNotFoundExceptionWhenDelete) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, e.message, e)
        }
    }
}