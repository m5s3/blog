package com.m5s3.blog.service

import com.m5s3.blog.service.dto.BlogParamS
import com.m5s3.blog.service.dto.BlogResultS
import com.m5s3.blog.service.dto.BlogWithCommentsResultS


interface BlogService {
    fun getBlogById(id: Long): BlogResultS

    fun getBlogWithComments(id: Long): BlogWithCommentsResultS

    fun createBlog(param: BlogParamS): BlogResultS

    fun modifyBlogById(blogId: Long, param: BlogParamS): BlogResultS

    fun deleteBlogById(blogId: Long)
}