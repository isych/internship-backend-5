package com.exadel.backendservice.security;

import com.exadel.backendservice.services.impl.UserServiceImpl;
import io.jsonwebtoken.*;
import lombok.extern.java.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Component
@Log
public class JwtProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtProvider.class);

    @Value("$(jwt.secret)")
    private String jwtSecret;

    public String generateToken(String login) {
        Date date = Date.from(LocalDateTime.now().plusDays(1).atZone(ZoneId.systemDefault()).toInstant());
        LOGGER.info("Generated new token for user with login is " + login);
        return Jwts.builder()
                .setSubject(login)
                .setExpiration(date)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
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
        Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }
}
