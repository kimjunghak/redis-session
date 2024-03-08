package com.redis.common.config

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.security.oauth2.client.web.AuthorizationRequestRepository
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest
import org.springframework.stereotype.Component
import org.springframework.util.Assert

@Component
class RedisAuthorizationRequestRepository(
    @Qualifier("oauth2RedisSession") private val redisTemplate: RedisTemplate<String, OAuth2AuthorizationRequest>,
): AuthorizationRequestRepository<OAuth2AuthorizationRequest> {

    companion object {
        private const val REDIS_ATTRIBUTE_NAME: String = "REDIS_ATTRIBUTE_NAME"
    }

    override fun loadAuthorizationRequest(request: HttpServletRequest?): OAuth2AuthorizationRequest? {
        Assert.notNull(request, "request cannot be null")
        val stateParameter = this.getStateParameter(request!!)
        val authorizationRequest = this.getAuthorizationRequest()
        return if (authorizationRequest != null && stateParameter == authorizationRequest.state) authorizationRequest else null
    }

    override fun removeAuthorizationRequest(
        request: HttpServletRequest?,
        response: HttpServletResponse?
    ): OAuth2AuthorizationRequest? {
        Assert.notNull(response, "response cannot be null")
        val authorizationRequest = loadAuthorizationRequest(request)
        if(authorizationRequest != null) redisTemplate.delete(REDIS_ATTRIBUTE_NAME)
        return authorizationRequest
    }

    override fun saveAuthorizationRequest(
        authorizationRequest: OAuth2AuthorizationRequest?,
        request: HttpServletRequest?,
        response: HttpServletResponse?
    ) {
        if (authorizationRequest == null) {
            removeAuthorizationRequest(request, response)
        } else {
            val state = authorizationRequest.state
            Assert.hasText(state, "authorizationRequest.state cannot be empty")
            redisTemplate.opsForValue().set(REDIS_ATTRIBUTE_NAME, authorizationRequest)
        }
    }

    private fun getStateParameter(request: HttpServletRequest): String = request.getParameter("state")

    private fun getAuthorizationRequest(): OAuth2AuthorizationRequest? {
        return redisTemplate.opsForValue().get(REDIS_ATTRIBUTE_NAME)
    }
}