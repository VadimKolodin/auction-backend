package ru.stud.auc.config.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;


@Data
@ConfigurationProperties(prefix = "auth")
public class AuthenticationProperties {

    private long expirationMillis;

    private long refreshExpirationMillis;

    private String secretKey;

    private String[] permitAll;

}
