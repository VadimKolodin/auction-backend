package ru.stud.auc.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.stud.auc.users.UsersAuthMapper;
import ru.stud.auc.exception.NotFoundException;
import ru.stud.auc.users.UsersAuthRepository;

@Configuration
@RequiredArgsConstructor
public class AuthenticationConfig {

  private final UsersAuthMapper mapper;
  private final UsersAuthRepository repository;

  @Bean
  public UserDetailsService userDetailsService() {
    return username -> mapper.toPojo(repository.findByLogin(username)
        .orElseThrow(() -> new NotFoundException("Пользователь с таким логином не найден")));
  }

  @Bean
  public AuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
    authProvider.setUserDetailsService(userDetailsService());
    authProvider.setPasswordEncoder(passwordEncoder());
    return authProvider;
  }

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
    return config.getAuthenticationManager();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

}
