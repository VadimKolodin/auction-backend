package ru.stud.auc.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.authentication.AuthenticationManagerBeanDefinitionParser;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.stud.auc.auth.model.UserAuthPojo;


@Configuration
@RequiredArgsConstructor
public class AuthenticationConfig {

  @Bean
  public UserDetailsService userDetailsService() {
    return username -> {
      UserAuthPojo pojo = new UserAuthPojo();
      pojo.setLogin(username);
      pojo.setPassword(username);
      return pojo;
    };
  }

  @Bean
  public AuthenticationProvider authenticationProvider() {
    return new AuthenticationManagerBeanDefinitionParser.NullAuthenticationProvider();
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
