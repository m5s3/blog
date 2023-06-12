package com.m5s3.blog.service.impl

import com.m5s3.blog.domain.MemberEntity
import com.m5s3.blog.repository.MemberRepository
import com.m5s3.blog.service.MemberService
import com.m5s3.blog.service.dto.MemberParamS
import com.m5s3.blog.service.dto.MemberResultS
import com.m5s3.blog.service.dto.toEntity
import com.m5s3.blog.service.exception.DataNotFoundException
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class MemberServiceImpl(
    private val memberRepository: MemberRepository
): MemberService {
    @Transactional(readOnly = true)
    override fun getMemberById(id: Long): MemberResultS {
        val memberEntity: MemberEntity =
            memberRepository.findByIdOrNull(id)
                ?: throw DataNotFoundException("Not Found Error(${id})")

        return MemberResultS(memberEntity)
    }

    @Transactional(readOnly = true)
    override fun getMemberByUserId(userId: String): MemberResultS? {
        val memberEntity =
            memberRepository.findByUserId(userId)
                ?: throw DataNotFoundException("Not Found Error(${userId})")
        return MemberResultS(memberEntity)
    }

    @Transactional(readOnly = true)
    override fun getMembers(pageable: Pageable): Page<MemberResultS> {
        val memberEntities: Page<MemberEntity> = memberRepository.findAll(pageable)
        return memberEntities.map {
            MemberResultS(it)
        }
    }

    @Transactional(readOnly = false)
    override fun createMember(param: MemberParamS): MemberResultS {
        val memberEntity: MemberEntity = memberRepository.save(param.toEntity())
        return MemberResultS(memberEntity)
    }

    @Transactional(readOnly = false)
    override fun modifyMemberById(id: Long, param: MemberParamS): MemberResultS {
        TODO("Not yet implemented")
    }

    @Transactional(readOnly = false)
    override fun deleteMemberById(id: Long) {
        TODO("Not yet implemented")
    }
}