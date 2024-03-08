package com.redis.common.service

import com.redis.member.domain.response.MemberProfile
import com.redis.member.domain.response.OAuthAttributes
import com.redis.member.service.MemberDataService
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.stereotype.Service

@Service
class OAuth2MemberService(
    private val memberDataService: MemberDataService,
): DefaultOAuth2UserService() {

    override fun loadUser(userRequest: OAuth2UserRequest?): OAuth2User {
        val loadUser = super.loadUser(userRequest)

        val registrationId = userRequest!!.clientRegistration.registrationId
        val attributes: Map<String, Any> = loadUser.attributes

        val memberProfile: MemberProfile = OAuthAttributes.extract(registrationId, attributes)
        val member = memberProfile.toEntity()
        memberDataService.save(member)

        return loadUser
    }
}