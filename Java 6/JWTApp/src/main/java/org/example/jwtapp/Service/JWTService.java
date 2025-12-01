package org.example.jwtapp.Service;

import ch.qos.logback.core.net.server.Client;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Jwts;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JWTService {
    public Key getSigningKey(){
        String secret = "012345678901234567890123456789012345678901234567890123456789012345";
        return Keys.hmacShaKeyFor(secret.getBytes());
    }
   public String create (UserDetails user, int expirySeconds){
       long now = System.currentTimeMillis();
         return Jwts.builder()
                 .setClaims(Map.of("name", "Poly"))
                 .setSubject(user.getUsername())
                 .setIssuedAt(new Date(now))
                 .setExpiration(new Date (now + expirySeconds * 1000L))
                 .signWith(this.getSigningKey(), SignatureAlgorithm.HS256)
                 .compact();
   }
    public Claims getBody(String token){
        return Jwts.parserBuilder()
                .setSigningKey(this.getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean validate(Claims claims){
        return claims.getExpiration().after(new Date());
    }

}