package com.yulcom.inoutfolderapp.infrastructure.security.utils;

import com.yulcom.inoutfolderapp.domain.entities.CorporateUser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtUtils {
    @Value("${server.security.jwt.secret}")
    private String jwtSecret;
    private final long jwtExpirationMs = 86400000;


    public String generateToken(CorporateUser user) {
        return Jwts.builder()
            .setSubject(user.getUsername())
            .setClaims(generateClaims(user))
            .setIssuedAt(new Date())
            .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
            .signWith(SignatureAlgorithm.HS512, jwtSecret)
            .compact();
    }

    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }

    public Map<String, Object> generateClaims(CorporateUser user)
    {
        var claims = new HashMap<String, Object>();
        claims.put("id", user.getId());
        claims.put("roles", user.getRoles().stream().map(role -> role.getName().name()).toList());
        return claims;
    }
}
