package com.redis.member.service

import com.redis.member.domain.entity.Member
import com.redis.member.repository.MemberRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class MemberDataService(
    private val memberRepository: MemberRepository,
) {

    @Transactional
    fun save(member: Member) = memberRepository.save(member)
}