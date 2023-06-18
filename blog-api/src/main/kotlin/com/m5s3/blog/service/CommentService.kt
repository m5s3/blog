package com.m5s3.blog.service

import com.m5s3.blog.service.dto.CommentParamS
import com.m5s3.blog.service.dto.CommentResultS

interface CommentService {
    fun createComment(param: CommentParamS): CommentResultS
}