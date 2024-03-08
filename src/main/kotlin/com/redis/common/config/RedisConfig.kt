package com.redis.common.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest

@Configuration
class RedisConfig {

    @Bean
    fun oauth2RedisSession(redisConnectionFactory: RedisConnectionFactory): RedisTemplate<String, OAuth2AuthorizationRequest> {
        val redisTemplate = RedisTemplate<String, OAuth2AuthorizationRequest>()
        redisTemplate.connectionFactory = redisConnectionFactory
        return redisTemplate
    }

}