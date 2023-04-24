package ru.stud.auc.auth.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.stud.auc.auth.model.UserAuthPojo;
import ru.stud.auc.auth.token.model.TokenDto;
import ru.stud.auc.config.property.GenerationAuthenticationProperties;

import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class JwtGeneratorService {

    private final JwtReaderService readerService;

    private final GenerationAuthenticationProperties properties;


    public TokenDto generateToken(UserAuthPojo userDetails) {
        return generateToken(new HashMap<>(), userDetails, properties.getExpirationMillis());
    }

    public TokenDto generateRefreshToken(UserAuthPojo userDetails) {
        return generateToken(new HashMap<>(), userDetails, properties.getRefreshExpirationMillis());
    }

    public TokenDto generateToken(Map<String, Object> extraClaims, UserAuthPojo userDetails, long expirationMillis) {
        Date now = new Date(System.currentTimeMillis());
        Date expiration = new Date(System.currentTimeMillis() + expirationMillis);
        String token = Jwts.builder()
                           .setClaims(extraClaims)
                           .setHeaderParam("userId", userDetails.getId())
                           .setHeaderParam("role", userDetails.getRole().name())
                           .setHeaderParam("login", userDetails.getUsername())
                           .setIssuedAt(now)
                           .setExpiration(expiration)
                           .signWith(readerService.getSignInKey(), SignatureAlgorithm.HS256)
                           .compact();
        return new TokenDto(token, expiration.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
    }

}
