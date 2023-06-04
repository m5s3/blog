package com.m5s3.blog.controller.v1.dto

import com.m5s3.blog.domain.MemberEntity
import com.m5s3.blog.service.dto.MemberParamS
import com.m5s3.blog.service.dto.MemberResultS

data class MemberParamC(
    val id: Long? = 0,
    val userId: String,
    val password: String,
    val email: String,
    val nickName: String,
    val memo: String
)

fun MemberParamC.toS() = MemberParamS(
    id = id,
    userId = userId,
    password = password,
    email = email,
    nickName = nickName,
    memo = memo
)
data class MemberResultC(
    val id: Long,
    val userId: String,
    val password: String,
    val email: String,
    val nickName: String,
    val memo: String
) {
    companion object {
        operator fun invoke(memberResultS: MemberResultS) =
            with(memberResultS) {
                MemberResultC(
                    id = id,
                    userId = userId,
                    password = password,
                    email = email,
                    nickName = nickName,
                    memo = memo
                )
            }
    }
}

