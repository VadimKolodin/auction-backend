package ru.stud.auc.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.TimeZone;
import java.util.function.BiFunction;

public class ApplicationUtils {

    public static <T> ApplicationContext runApplication(BiFunction<Class<T>, String[], ApplicationContext> run,
                                                        String[] args,
                                                        Class<T> clazz,
                                                        Logger log) {
        TimeZone.setDefault(TimeZone.getTimeZone("GMT"));
        ApplicationContext context = run.apply(clazz, args);
        Environment environment = context.getEnvironment();
        String protocol = "http";
        String serverPort = environment.getProperty("server.port");
        String contextPath = environment.getProperty("server.servlet.context-path");
        if (!StringUtils.isEmpty(contextPath)) {
            if (contextPath.endsWith("/"))
                contextPath = contextPath.substring(0, contextPath.length() - 1);
        } else {
            contextPath = "";
        }
        String hostAddress = "localhost";
        try {
            hostAddress = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            log.warn("The host name could not be determined, using `localhost` as fallback");
        }

        log.info("\n----------------------------------------------------------\n\t" + "Application '{}' is running! Access URLs:\n\t" +
                         "Local: \t\t{}://localhost:{}{}/api/swagger-ui/\n\t" + "External: \t{}://{}:{}{}/api/swagger-ui/\n\t" +
                         "Profile(s): \t{}\n----------------------------------------------------------",
                 environment.getProperty("spring.application.name"),
                 protocol,
                 serverPort,
                 contextPath,
                 protocol,
                 hostAddress,
                 serverPort,
                 contextPath,
                 environment.getActiveProfiles());
        return context;
    }
}
