package migrations;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.stud.auc.util.ApplicationUtils;

@Slf4j
@SpringBootApplication
public class MigrationsApplication {

    public static void main(String[] args) {
        SpringApplication.exit(ApplicationUtils.runApplication(SpringApplication::run, args, MigrationsApplication.class, log));
    }
}

