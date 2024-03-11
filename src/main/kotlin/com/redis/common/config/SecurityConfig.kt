package com.redis.common.config

import com.redis.common.service.OAuth2MemberService
import org.springframework.boot.autoconfigure.security.servlet.PathRequest
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val oAuth2MemberService: OAuth2MemberService,
) {

    @Bean
    fun securityFilter(http: HttpSecurity): SecurityFilterChain {
        return http
            .csrf { it.disable() }
            .authorizeHttpRequests {
                it.requestMatchers(PathRequest.toH2Console()).permitAll()
            }
            .headers { it.frameOptions { option -> option.sameOrigin()} }
            .oauth2Login {
                it.userInfoEndpoint {
                        user -> user.userService(oAuth2MemberService)
                }
            }
            .build()
    }
}