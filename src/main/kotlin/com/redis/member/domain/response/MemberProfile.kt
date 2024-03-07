package com.redis.member.domain.response

import com.redis.member.domain.entity.Member

class MemberProfile(
    val registrationId: String,
    val clientId: String,
    val nickname: String?,
    val profileImage: String?,
) {
    fun toEntity(): Member =
        Member(
            registrationId = this.registrationId,
            clientId = this.clientId,
            nickname = this.nickname,
            profileImage = this.profileImage
        )
}