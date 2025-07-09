package dev.iamwallace.user.infrastructure.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JwtUtil {
  @Autowired
  private Environment env; //secretKey;

  public String generateToken(String username) {
    // The secret key was added to intellij in configurations, we can move to env file later.
    String secretKey = env.getProperty("SECRET_KEY");

    return Jwts.builder()
      .setSubject(username)
      .setIssuedAt(new Date())
      .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
      .signWith(Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8)), SignatureAlgorithm.HS256)
      .compact();
  }

  public Claims extractClaims(String token) {
    String secretKey = env.getProperty("SECRET_KEY");

    return Jwts.parser()
      .setSigningKey((Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8))))
      .build()
      .parseClaimsJws(token)
      .getPayload();
  }

  public String extractUsername(String token) {
    return extractClaims(token).getSubject();
  }

  public boolean isTokenExpired(String token) {
    return extractClaims(token).getExpiration().before(new Date());
  }

  public boolean validateToken(String token, String username) {
    final String extractedUsername = extractUsername(token);
    return (extractedUsername.equals(username) && !isTokenExpired(token));
  }
}
