package com.jobconnect.jobconnect.service;

import java.security.Key; 
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.stereotype.Service;

import com.jobconnect.jobconnect.model.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

	private static final String SECRET_KEY = "12345678901234567890123456789012";
	
	private Key getSignInKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }
	
	// Generate JWT token
    public String generateToken(User user) {
    	Map<String, Object> claims = new HashMap<>();
        claims.put("role", user.getRole().name());
    	
        return Jwts.builder()
        		.setClaims(claims)
                .setSubject(user.getEmail())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 hours
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }
	
	public String extractUserName(String token) {
		return extractClaim(token, Claims::getSubject);
	}
	
	public String extractRole(String token) {
	    return extractClaim(token, claims -> claims.get("role", String.class));
	}
	
	// Extract any claim
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = parseToken(token);
        return claimsResolver.apply(claims);
    }
    
    // Validate token
    public boolean isTokenValid(String token, String username) {
        final String extractedUsername = extractUserName(token);
        return (extractedUsername.equals(username)) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
    
    private Claims parseToken(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
