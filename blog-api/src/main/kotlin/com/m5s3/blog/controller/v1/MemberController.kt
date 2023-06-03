package com.m5s3.blog.controller.v1

import com.m5s3.blog.controller.v1.dto.MemberParamC
import com.m5s3.blog.controller.v1.dto.MemberResultC
import com.m5s3.blog.controller.v1.dto.toS
import com.m5s3.blog.service.MemberService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/member")
class MemberController(
    private val memberService: MemberService
) {

    @PostMapping
    fun create(
        @RequestBody request: MemberParamC
    ) = MemberResultC(memberService.createMember(request.toS()))

}