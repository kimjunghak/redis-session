package com.redis.member.repository

import com.redis.member.domain.entity.Member
import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository: JpaRepository<Member, Long>