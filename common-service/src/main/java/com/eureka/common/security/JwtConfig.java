package com.eureka.common.security;

import org.springframework.beans.factory.annotation.Value;

import lombok.Getter;

@Getter
public class JwtConfig {
    @Value("${security.jwt.uri.auth:/auth/**}")
    private String UriAuth;
    
    @Value("${security.jwt.uri.public:/**/public/**}")
    private String UriPublic;

    @Value("${security.jwt.header:Authorization}")
    private String header;

    @Value("${security.jwt.prefix:Bearer }")
    private String prefix;

    @Value("${security.jwt.expiration:#{24*60*60}}")
    private int expiration;

    @Value("${security.jwt.secret:JwtSecretKey}")
    private String secret;
}