package com.global.Service;


import java.nio.charset.StandardCharsets;
import java.util.Date;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;


@Service
public class JWTService {
	
	
	@Value("${jwt.secret}")
	private String key;
	@Value("${jwt.expiration}")
	private long expiration;
	
	
	public SecretKey getSingingKey() {
		return Keys.hmacShaKeyFor(key.getBytes(StandardCharsets.UTF_8));
	}
	
	
	public String GenerateToken(UserDetails user) {
		long start = System.currentTimeMillis();
		long end = expiration+start;
		return Jwts.builder().subject(user.getUsername()).issuedAt(new Date(start)).expiration(new Date(end)).signWith(getSingingKey()).compact();
	}
	
	public String extractUserName(String token) {
		return extractClaims(token).getSubject();
	}
	
	public Claims extractClaims(String token) {
		return Jwts.parser().verifyWith(getSingingKey()).build().parseSignedClaims(token).getPayload();	
	}
	
	public boolean isValidToken(String token,UserDetails user) {
		String username =  extractUserName(token);
		return username.equals(user.getUsername()) && !isTokenExpired(token);
		
	}
	
	public boolean isTokenExpired(String token) {
		return extractClaims(token).getExpiration().before(new Date());
	}

}
