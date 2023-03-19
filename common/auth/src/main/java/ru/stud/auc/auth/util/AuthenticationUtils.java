package ru.stud.auc.auth.util;

import org.springframework.security.core.context.SecurityContextHolder;

import java.util.UUID;

public class AuthenticationUtils {

    public static UUID getUserId() {
        return (UUID) SecurityContextHolder.getContext().getAuthentication().getCredentials();
    }

}
