package com.redis.common.config

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.security.oauth2.client.web.AuthorizationRequestRepository
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest
import org.springframework.stereotype.Component

@Component
class RedisAuthorizationRequestRepository(): AuthorizationRequestRepository<OAuth2AuthorizationRequest> {

    private val redisTemplate: RedisTemplate<String, Any> = RedisTemplate<String, Any>()
    override fun loadAuthorizationRequest(request: HttpServletRequest?): OAuth2AuthorizationRequest {
        TODO("Not yet implemented")
    }

    override fun removeAuthorizationRequest(
        request: HttpServletRequest?,
        response: HttpServletResponse?
    ): OAuth2AuthorizationRequest {
        TODO("Not yet implemented")
    }

    override fun saveAuthorizationRequest(
        authorizationRequest: OAuth2AuthorizationRequest?,
        request: HttpServletRequest?,
        response: HttpServletResponse?
    ) {
        TODO("Not yet implemented")
    }

}