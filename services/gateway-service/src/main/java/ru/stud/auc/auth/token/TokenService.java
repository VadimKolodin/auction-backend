package ru.stud.auc.auth.token;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import ru.stud.auc.auth.jwt.JwtReaderService;
import ru.stud.auc.users.UsersAuthMapper;
import ru.stud.auc.auth.jwt.JwtGeneratorService;
import ru.stud.auc.auth.model.UserAuthPojo;
import ru.stud.auc.auth.token.model.AuthenticationDto;
import ru.stud.auc.auth.token.model.TokenDto;
import ru.stud.auc.exception.BadRequestException;
import ru.stud.auc.exception.UnauthorizedException;
import ru.stud.auc.auth.model.auth.RefreshTokenEntity;
import ru.stud.auc.auth.model.auth.RefreshTokenRepository;
import ru.stud.auc.auth.model.auth.TokenEntity;
import ru.stud.auc.auth.model.auth.TokenRepository;
import ru.stud.auc.users.UsersGetter;

import javax.transaction.Transactional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class TokenService {

    private final TokenRepository tokenRepository;

    private final UsersGetter usersGetter;

    private final UsersAuthMapper mapper;

    private final RefreshTokenRepository refreshTokenRepository;

    private final JwtGeneratorService jwtGeneratorService;

    private final JwtReaderService jwtReaderService;

    public boolean isTokenExistAndValid(String token, UserDetails userDetail) {
        TokenEntity tokenEntity = tokenRepository.findByTokenValue(token).orElseThrow( () -> new UnauthorizedException("Токен устарел или не существует"));
        if (jwtReaderService.isTokenValid(token, userDetail)) {
            return true;
        }
        tokenRepository.deleteTokenById(tokenEntity.getId());
        return false;
    }

    @Transactional
    public AuthenticationDto generateTokens(UserAuthPojo pojo) {
        TokenDto tokenDto = deleteOldAndCreateToken(pojo);
        TokenDto refreshTokenDto = deleteOldAndCreateRefreshToken(pojo);
        return mapper.toDto(tokenDto, refreshTokenDto);

    }

    @Transactional
    public TokenDto generateByRefreshToken(String refreshToken) {
        RefreshTokenEntity refreshTokenEntity =
                refreshTokenRepository.findByRefreshTokenValue(refreshToken)
                                      .orElseThrow(() -> new UnauthorizedException("Токен устарел или не существует"));
        UserAuthPojo userAuthPojo = usersGetter.getAuthPojoById(refreshTokenEntity.getUserId());
        if (jwtReaderService.isTokenValid(refreshToken, userAuthPojo)) {
            return deleteOldAndCreateToken(userAuthPojo);
        }
        refreshTokenRepository.deleteRefreshTokenById(refreshTokenEntity.getId());
        throw new BadRequestException("Токен устарел или невалиден");

    }

    @Transactional(value = Transactional.TxType.MANDATORY)
    protected TokenDto deleteOldAndCreateToken(UserAuthPojo pojo) {
        tokenRepository.deleteTokenByUserId(pojo.getId());
        TokenDto tokenDto = jwtGeneratorService.generateToken(pojo);
        TokenEntity tokenEntity = new TokenEntity();
        tokenEntity.setId(UUID.randomUUID());
        tokenEntity.setToken(tokenDto.getToken());
        tokenEntity.setUserId(pojo.getId());
        tokenRepository.persist(tokenEntity);
        return tokenDto;
    }

    @Transactional(value = Transactional.TxType.MANDATORY)
    protected TokenDto deleteOldAndCreateRefreshToken(UserAuthPojo pojo) {
        refreshTokenRepository.deleteRefreshTokenByUserId(pojo.getId());
        TokenDto refreshTokenDto = jwtGeneratorService.generateRefreshToken(pojo);
        RefreshTokenEntity refreshTokenEntity = new RefreshTokenEntity();
        refreshTokenEntity.setId(UUID.randomUUID());
        refreshTokenEntity.setToken(refreshTokenDto.getToken());
        refreshTokenEntity.setUserId(pojo.getId());
        refreshTokenRepository.persist(refreshTokenEntity);
        return refreshTokenDto;
    }

    public String getLoginFromToken(String token) {
        return jwtReaderService.extractUsername(token);
    }

}
