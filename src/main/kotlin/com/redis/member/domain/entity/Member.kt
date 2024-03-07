package com.redis.member.domain.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class Member(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val registrationId: String,
    val clientId: String,
    val nickname: String? = null,
    val profileImage: String? = null,
)