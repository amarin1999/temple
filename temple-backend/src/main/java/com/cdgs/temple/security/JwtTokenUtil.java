package com.cdgs.temple.security;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.TextCodec;
import io.jsonwebtoken.impl.crypto.MacProvider;

@Component
public class JwtTokenUtil implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 8327290745792091836L;

    private static final String CLAM_KEY_USERNAME = "username";
    private static final String CLAM_KEY_ID = "account_id";
    private static final String CLAM_KEY_CREATED = "created";
    private SecretKey key = MacProvider.generateKey(SignatureAlgorithm.HS512);
//    private String base64Encoded = TextCodec.BASE64.encode(key.getEncoded());
    
    private String base64Encoded = TextCodec.BASE64.encode("eyJhbGciOiJIUzUxMiJ9.eyJhY2NvdW50X2lkIjoxLCJjcmVhdGVkIjoxNTUyMzgzMDMxNzQ0LCJleHAiO");
    
    @Value("${jwt.expiration}")
    private Long expiration;


    public String getUsernameFromToken(String token) {

        String username;
        try {
            final Claims claims = getClaimsFromToken(token);
            username = claims.get(CLAM_KEY_USERNAME).toString();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }

    private Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser().setSigningKey(base64Encoded).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;

    }

    public boolean validateToken(String token, UserDetails userDetails) {
        JwtUser user = (JwtUser) userDetails;
        final String username = getUsernameFromToken(token);
        return (username.equals(user.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    private Date getExpirationDateFromToken(String token) {
        Date expiration;
        try {
            final Claims claims = getClaimsFromToken(token);
            if (claims != null) {
                expiration = claims.getExpiration();
            } else {
                expiration = null;
            }
        } catch (Exception e) {
            expiration = null;
        }
        return expiration;
    }

    public String generateToken(JwtUser userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAM_KEY_ID, userDetails.getId());
        claims.put(CLAM_KEY_USERNAME, userDetails.getUsername());
        claims.put(CLAM_KEY_CREATED, new Date());
        return generateToken(claims);

    }

    private String generateToken(Map<String, Object> claims) {
        return Jwts
                .builder()
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512, base64Encoded)
                .compact();
    }

    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + expiration * 1000);
    }
}
