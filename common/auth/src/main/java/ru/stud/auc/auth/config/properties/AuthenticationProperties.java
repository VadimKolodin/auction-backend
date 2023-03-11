package ru.stud.auc.auth.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;


@Data
@ConfigurationProperties(prefix = "auth")
public class AuthenticationProperties {

    private String secretKey;

    private String[] permitAll;

}
