package com.product.util;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;

@Component
public class JWTUtil {
	@Value("${jwt.signature}")
	private String jwtSignature;

	private SecretKey secret_key;

	@PostConstruct
	public void assignKey() {
		secret_key = Keys.hmacShaKeyFor(jwtSignature.getBytes(StandardCharsets.UTF_8));
	}

	public String createJwtToken(String username, List<String> roles) {
		String token = Jwts.builder().subject(username).claim("roles", roles).issuedAt(new Date())
				.expiration(new Date(System.currentTimeMillis() + (3600 * 1000))).signWith(secret_key).compact();
		return token;

	}

	public boolean isTokenExpired(String token) {
		Date expire = Jwts.parser().verifyWith(secret_key).build().parseSignedClaims(token).getPayload()
				.getExpiration();
		return expire.before(new Date());
	}
}
