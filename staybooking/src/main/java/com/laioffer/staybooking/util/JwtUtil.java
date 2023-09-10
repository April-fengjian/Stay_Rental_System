package com.laioffer.staybooking.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;

@Component
public class JwtUtil {
    @Value("${jwt.secret}")
    private String secret;

    //HS256加密方法是公开的，但是secret密钥是自己设定的不公开的
    //根据builder里提供的信息加密，所以每个人的token都是不一样的
    public String generateToken(String subject) {
        return Jwts.builder()
                .setClaims(new HashMap<>()) //存一些用户信息
                .setSubject(subject) //username存在subject里
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }
    private Claims extractClaims(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    public String extractUsername(String token) {
        return extractClaims(token).getSubject();
    }

    public Date extractExpiration(String token) {
        return extractClaims(token).getExpiration();
    }

    public Boolean validateToken(String token) {
        return extractExpiration(token).after(new Date());
    }
}
