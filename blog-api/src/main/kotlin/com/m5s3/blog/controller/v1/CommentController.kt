package com.m5s3.blog.controller.v1

import com.m5s3.blog.controller.v1.dto.CommentParamC
import com.m5s3.blog.controller.v1.dto.CommentResultC
import com.m5s3.blog.controller.v1.dto.MemberParamC
import com.m5s3.blog.controller.v1.dto.toS
import com.m5s3.blog.service.CommentService
import com.m5s3.blog.service.exception.DataNotFoundExceptionWhenDelete
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/api/v1/comment")
class CommentController(
    private val commentService: CommentService
) {

    @PostMapping("/new")
    fun createComment(
        @RequestBody request: CommentParamC
    ) : CommentResultC {
        val member = MemberParamC(1, "test", "test", "test@test.com", "test", "test")
        return CommentResultC(commentService.createComment(request.toS(member)))
    }

    @DeleteMapping("/{commentId}/delete")
    fun deleteComment(
        @PathVariable commentId: Long
    ) {
        try {
            commentService.deleteCommentById(commentId)
        } catch (e: DataNotFoundExceptionWhenDelete) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, e.message, e)
        }
    }
}