## Redis, Session 저장소로 사용하기

Spring Security OAuth2 Client를 이용할 때 서버 이중화를 하면 `AuthorizationRequestRepository의` 기본 구현체인 `HttpSessionOAuth2AuthorizationRequestRepository`에 의해서 OAuth2 인증 결과를 Session에 저장하기 때문에 로그인이 정상적으로 되지 않는다.

예전에 프로젝트를 할 때는 쿠키로 해결을 했었지만 요즘에는 Redis를 Session 저장소로 많이 활용한다고 하여 Redis를 활용하여 OAuth2 로그인을 구현해 보았다.