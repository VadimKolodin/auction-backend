package ru.stud.auc.config.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;


@Data
@ConfigurationProperties(prefix = "auth.gen")
public class GenerationAuthenticationProperties {

    private long expirationMillis;

    private long refreshExpirationMillis;


}
