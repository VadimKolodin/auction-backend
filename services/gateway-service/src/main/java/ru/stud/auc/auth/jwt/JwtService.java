package ru.stud.auc.auth.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ru.stud.auc.auth.token.model.TokenDto;
import ru.stud.auc.config.property.AuthenticationProperties;

import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class JwtService {


    private final AuthenticationProperties properties;


    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public TokenDto generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails, properties.getExpirationMillis());
    }

    public TokenDto generateRefreshToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails, properties.getRefreshExpirationMillis());
    }

    public TokenDto generateToken(Map<String, Object> extraClaims, UserDetails userDetails, long expirationMillis) {
        Date now = new Date(System.currentTimeMillis());
        Date expiration = new Date(System.currentTimeMillis() + expirationMillis);
        String token = Jwts.builder()
                   .setClaims(extraClaims)
                   .setSubject(userDetails.getUsername())
                   .setIssuedAt(now)
                   .setExpiration(expiration)
                   .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                   .compact();
        return new TokenDto(token, expiration.toInstant()
                                             .atZone(ZoneId.systemDefault())
                                             .toLocalDateTime());
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(getSignInKey()).build().parseClaimsJws(token).getBody();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(properties.getSecretKey());
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
