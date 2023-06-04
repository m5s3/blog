package com.m5s3.blog.service.dto

import com.m5s3.blog.domain.MemberEntity

data class MemberParamS(
    val id: Long? = 0,
    val userId: String,
    val password: String,
    val email: String,
    val nickName: String,
    val memo: String
)

fun MemberParamS.toEntity() = MemberEntity(
    userId = userId,
    password = password,
    email = email,
    nickName = nickName,
    memo = memo
)

fun MemberEntity.toResultS() = MemberResultS(
    id = id!!,
    userId = userId,
    password = password,
    email = email,
    nickName = nickName,
    memo = memo
)

data class MemberResultS(
    val id: Long,
    val userId: String,
    val password: String,
    val email: String,
    val nickName: String,
    val memo: String
) {
    companion object {
        operator fun invoke(memberEntity: MemberEntity) =
            with(memberEntity) {
                MemberResultS(
                    id = id!!,
                    userId = userId,
                    password = password,
                    email = email,
                    nickName = nickName,
                    memo = memo
                )
            }
    }
}

