package com.wedeliver.serviceaccount.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.wedeliver.serviceaccount.domain.Jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

@Component
public class JwtCreator {
    @Autowired
    private RSAPrivateKey privateKey;
    @Autowired
    private RSAPublicKey publicKey;

    public Jwt createJwtForClaims(final String subject, final Map<String, String> claims) {
        final Calendar expiresAt = Calendar.getInstance();
        expiresAt.setTimeInMillis(Instant.now().toEpochMilli());
        expiresAt.add(Calendar.DATE, 1);

        final JWTCreator.Builder jwtBuilder = JWT.create().withSubject(subject);

        claims.forEach(jwtBuilder::withClaim);

        return new Jwt(jwtBuilder
                .withNotBefore(new Date())
                .withExpiresAt(expiresAt.getTime())
                .sign(Algorithm.RSA256(publicKey, privateKey)));
    }

    // public Jwt createJwtForClaims(final String subject, final Map<String, Object> claims){
    //     long nowMillis = System.currentTimeMillis();
    //     long expMillis = nowMillis + tokenValidity;
    //     Date exp = new Date(expMillis);
    //     // Create the JWT object
    //     return new Jwt(
    //         Jwts.builder()
    //         .setSubject(subject)
    //         .setClaims(claims)
    //         .setIssuedAt(new Date(nowMillis))
    //         .setExpiration(exp)
    //         .signWith(SignatureAlgorithm.HS512, jwtSecret).compact()
    //     );
    // }

}
