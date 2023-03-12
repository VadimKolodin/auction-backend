package ru.stud.auc.user;


import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ru.stud.auc.util.YamlPropertySourceFactory;

@Configuration
@EnableFeignClients
@PropertySource(value = "classpath:application-users.yml", factory = YamlPropertySourceFactory.class)
public class UsersConfig {}
