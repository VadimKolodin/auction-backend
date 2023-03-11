package ru.stud.auc.auth.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ru.stud.auc.auth.config.properties.AuthenticationProperties;

import java.security.Key;
import java.util.Date;
import java.util.UUID;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class JwtReaderService {

    private final AuthenticationProperties properties;

    public String extractUserId(String token) {
        return (String) Jwts.parserBuilder().setSigningKey(getSignInKey()).build().parseClaimsJws(token).getHeader().get("userId");
    }

    public String extractUserRole(String token) {
        return (String) Jwts.parserBuilder().setSigningKey(getSignInKey()).build().parseClaimsJws(token).getHeader().get("role");
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(getSignInKey()).build().parseClaimsJws(token).getBody();
    }

    protected Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(properties.getSecretKey());
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
