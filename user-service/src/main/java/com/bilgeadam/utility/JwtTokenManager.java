package com.bilgeadam.utility;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class JwtTokenManager {
    @Value("${jwt.secretKey}")
    String secretKey;
   @Value("${jwt.audience}")
    String audience;
    @Value("${jwt.issuer}")
    String issuer;





    public Optional<DecodedJWT> validateToken(String token){
        try {
            Algorithm algorithm=Algorithm.HMAC512(secretKey);
            JWTVerifier verifier=JWT.require(algorithm).withIssuer(issuer).withAudience(audience).build();
            DecodedJWT decodedJWT= verifier.verify(token);
            if (decodedJWT==null){
                return  Optional.empty();
            }
            return Optional.of(decodedJWT);
        }catch (Exception exception){
            System.out.println(exception.getMessage());
            return Optional.empty();
        }
    }

    public Optional<Long> getIdFromToken(String token){
        Optional<DecodedJWT> decodedJWT=validateToken(token);
        if (decodedJWT.isPresent()){
            return  Optional.of(decodedJWT.get().getClaim("id").asLong());
        }
        return Optional.empty();
    }

    public Optional<String> getRoleFromToken(String token){
        try {
            Algorithm algorithm=Algorithm.HMAC512(secretKey);
            JWTVerifier verifier=JWT.require(algorithm).withIssuer(issuer).withAudience(audience).build();
            DecodedJWT decodedJWT= verifier.verify(token);
            if (decodedJWT==null){
                return  Optional.empty();
            }
            return  Optional.of(decodedJWT.getClaim("role").asString());
        }catch (Exception exception){
           exception.printStackTrace();
            return Optional.empty();
        }
    }
}
