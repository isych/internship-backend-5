package com.exadel.backendservice.security;

import io.jsonwebtoken.*;
import lombok.extern.java.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

@Component
@Log
public class JwtProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtProvider.class);

    @Value("${jwt.token.secret}")
    private String jwtSecret;

    @Value("${jwt.token.expired.minutes}")
    private long validityInMinutes;

    @PostConstruct
    protected void init() {
        jwtSecret = Base64.getEncoder().encodeToString(jwtSecret.getBytes());
    }

    public String generateToken(String login, String fullName, String roleName, UUID uuid) {
        Date now = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
        Date expDate = Date.from(LocalDateTime.now().plusMinutes(validityInMinutes).atZone(ZoneId.systemDefault()).toInstant());
        LOGGER.info("Generated new token for user with login is " + login);
        Map<String, Object> claimsMap = new HashMap<>();
        claimsMap.put("fullName", fullName);
        claimsMap.put("role", roleName.substring(5));
        claimsMap.put("id", uuid);
        return Jwts.builder()
                .setClaims(claimsMap)
                .setSubject(login)
                .setIssuedAt(now)
                .setExpiration(expDate)
                .signWith(SignatureAlgorithm.HS256, jwtSecret)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException expEx) {
            LOGGER.warn("Token expired");
        } catch (UnsupportedJwtException unsEx) {
            LOGGER.warn("Unsupported jwt");
        } catch (MalformedJwtException mjEx) {
            LOGGER.warn("Malformed jwt");
        } catch (SignatureException sEx) {
            LOGGER.warn("Invalid signature");
        } catch (Exception e) {
            LOGGER.warn("invalid token");
        }
        return false;
    }

    public String getLoginFromToken(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }
}
