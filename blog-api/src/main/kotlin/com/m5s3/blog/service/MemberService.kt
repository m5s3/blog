package com.m5s3.blog.service

import com.m5s3.blog.service.dto.MemberParamS
import com.m5s3.blog.service.dto.MemberResultS
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable


interface MemberService {
    fun getMemberByUserId(userId: String): MemberResultS?
    fun getMemberById(id: Long): MemberResultS
    fun getMembers(pageable: Pageable): Page<MemberResultS>
    fun createMember(param: MemberParamS): MemberResultS
    fun modifyMemberById(id: Long, param: MemberParamS): MemberResultS
    fun deleteMemberById(id: Long)
}