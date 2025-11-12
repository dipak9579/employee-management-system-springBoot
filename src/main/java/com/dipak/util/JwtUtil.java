package com.dipak.util;



import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
	
	 private static final String SECRET_KEY = "yourSuperSecretKeyForJWTsMustBeLongEnough12345"; // at least 32 chars
	 private static final long EXPIRATION_TIME = 1000 * 60 * 60;
	 
	 private final Key key=Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
	 
	 public String generateToken(String username,String role) {
		 return Jwts.builder()
				 .setSubject(username)
				 .claim("role",role)
				 .setIssuedAt(new Date())
				 .setExpiration(new Date(System.currentTimeMillis()+EXPIRATION_TIME))
				 .signWith(key,SignatureAlgorithm.HS256)
				 .compact();
	 }
	 
	 public String extractUsername(String token) {
		 return parseClaims(token).getBody().getSubject();
	 }
	 
	 public String extractRole(String token) {
		 return parseClaims(token).getBody().get("role",String.class);
	 }
	 
	 public boolean isTokenValid(String token) {
		 try {
			parseClaims(token);
			return true;
		} catch (JwtException | IllegalArgumentException e) {
			return false;
		}
	 }
	 
	 private Jws<Claims>parseClaims(String token){
		 return Jwts.parserBuilder()
				 .setSigningKey(key)
				 .build()
				 .parseClaimsJws(token);
	 }

}
