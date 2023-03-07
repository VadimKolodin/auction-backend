package ru.stud.auc.auth.token;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import ru.stud.auc.auth.jwt.JwtService;
import ru.stud.auc.auth.model.UserAuthPojo;
import ru.stud.auc.auth.token.model.TokenDto;
import ru.stud.auc.exception.BadRequestException;
import ru.stud.auc.exception.NotFoundException;
import ru.stud.auc.exception.UnauthorizedException;
import ru.stud.auc.flowdata.auth.RefreshTokenRepository;
import ru.stud.auc.flowdata.auth.TokenEntity;
import ru.stud.auc.flowdata.auth.TokenRepository;

import javax.persistence.Column;
import javax.transaction.Transactional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class TokenService {

    private final TokenRepository tokenRepository;

    private final RefreshTokenRepository refreshTokenRepository;

    private final JwtService jwtService;

    public boolean isTokenExistAndValid(String token, UserDetails userDetail) {
        TokenEntity tokenEntity = tokenRepository.findByTokenValue(token).orElseThrow( () -> new UnauthorizedException("Токен устарел или не существует"));
        if (jwtService.isTokenValid(token, userDetail)) {
            return true;
        }
        tokenRepository.deleteTokenById(tokenEntity.getId());
        return false;
    }

    @Transactional
    public TokenDto generateToken(UserAuthPojo pojo) {
        tokenRepository.deleteTokenByUserId(pojo.getId());
        TokenDto tokenDto = jwtService.generateToken(pojo);
        TokenEntity tokenEntity = new TokenEntity();
        tokenEntity.setId(UUID.randomUUID());
        tokenEntity.setToken(tokenDto.getToken());
        tokenEntity.setUserId(pojo.getId());
        tokenRepository.persist(tokenEntity);
        return tokenDto;

    }

    public String getLoginFromToken(String token) {
        return jwtService.extractUsername(token);
    }

}
