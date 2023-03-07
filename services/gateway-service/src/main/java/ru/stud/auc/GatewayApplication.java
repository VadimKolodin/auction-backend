package ru.stud.auc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import ru.stud.auc.util.ApplicationUtils;

@Slf4j
@SpringBootApplication
@ConfigurationPropertiesScan
@EnableConfigurationProperties
public class GatewayApplication {
    public static void main(String[] args) {
        ApplicationUtils.runApplication(SpringApplication::run, args, GatewayApplication.class, log);
    }
}

