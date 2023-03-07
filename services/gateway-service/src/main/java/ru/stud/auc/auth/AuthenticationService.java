package ru.stud.auc.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.stud.auc.users.UsersAuthMapper;
import ru.stud.auc.auth.model.UserAuthDto;
import ru.stud.auc.auth.model.UserRegistrationDto;
import ru.stud.auc.auth.token.TokenService;
import ru.stud.auc.auth.token.model.AuthenticationDto;
import ru.stud.auc.auth.token.model.TokenDto;
import ru.stud.auc.common.enums.ClientRole;
import ru.stud.auc.exception.NotFoundException;
import ru.stud.auc.flowdata.UsersRepository;
import ru.stud.auc.flowdata.user.UserEntity;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UsersRepository repository;
    private final TokenService tokenService;
    private final PasswordEncoder passwordEncoder;
    private final UsersAuthMapper mapper;
    private final AuthenticationManager authenticationManager;

    @Transactional
    public void register(UserRegistrationDto request) {
        UserEntity user = new UserEntity();
        user.setId(UUID.randomUUID());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setLogin(request.getLogin());
        user.setRole(ClientRole.CLIENT);
        repository.persistWithoutUser(user);
    }


    public AuthenticationDto generateNewTokens(UserAuthDto request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getLogin(),
                        request.getPassword()
                )
                                          );
        UserEntity user = repository.findByLogin(request.getLogin())
                                    .orElseThrow(() -> new NotFoundException("Пользователь с таким логином и паролем не найден"));
        return tokenService.generateTokens(mapper.toPojo(user));
    }

    public TokenDto generateNewToken(String refreshToken) {
        return tokenService.generateByRefreshToken(refreshToken);
    }


}
