package com.redis.member.domain.response

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import java.util.function.Function

enum class OAuthAttributes(
    private val registrationId: String,
    private val of: Function<Map<String, Any>, MemberProfile>,
) {
    KAKAO("KAKAO", {
        val mapper = ObjectMapper()
        val kakaoAccount: Map<String, Any> = mapper.convertValue(it["kakao_account"], object: TypeReference<Map<String, Any>>() {})
        val kakaoProfile: Map<String, String> = mapper.convertValue(kakaoAccount["profile"], object: TypeReference<Map<String, String>>() {})

        MemberProfile(
            registrationId = "KAKAO",
            clientId = it["id"].toString(),
            nickname = kakaoProfile["nickname"],
            profileImage = kakaoProfile["thumbnail_image_url"]
        )
    }),
    ;
    companion object {
        fun extract(registrationId: String, attributes: Map<String, Any>): MemberProfile =
            entries.toTypedArray().first { provider -> provider.registrationId.equals(registrationId, true) }.of.apply(attributes)
    }

}