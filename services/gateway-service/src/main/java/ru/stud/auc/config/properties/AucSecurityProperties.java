package ru.stud.auc.config.properties;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Getter
@Component
@ConfigurationProperties(prefix = "auc.security")
public class AucSecurityProperties {
    private String[] permitall;
}
