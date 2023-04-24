package ru.stud.auc.auth.util;

import org.apache.commons.lang3.EnumUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import ru.stud.auc.common.enums.ClientRole;

import java.util.Collection;
import java.util.Objects;
import java.util.UUID;

public class AuthenticationUtils {

    public static UUID getUserId() {
        return (UUID) SecurityContextHolder.getContext().getAuthentication().getCredentials();
    }

    public static ClientRole getRole() {
        Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        for (GrantedAuthority authority : authorities) {
            if (EnumUtils.isValidEnum(ClientRole.class, authority.getAuthority())) {
                return ClientRole.valueOf(authority.getAuthority());
            }
        }
        return null;
    }

    public static boolean isSeller() {
        Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        for (GrantedAuthority authority : authorities) {
            if (EnumUtils.isValidEnum(ClientRole.class, authority.getAuthority())) {
                return Objects.equals(ClientRole.SELLER, ClientRole.valueOf(authority.getAuthority()));
            }
        }
        return false;
    }

}
