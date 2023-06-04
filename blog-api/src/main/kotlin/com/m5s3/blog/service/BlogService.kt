package com.m5s3.blog.service

import com.m5s3.blog.service.dto.BlogParamS
import com.m5s3.blog.service.dto.BlogResultS


interface BlogService {
    fun getBlogById(id: Long): BlogResultS
    fun createBlog(param: BlogParamS): BlogResultS

    fun modifyBlogById(blogId: Long, param: BlogParamS): BlogResultS

    fun deleteBlogById(blogId: Long)
}