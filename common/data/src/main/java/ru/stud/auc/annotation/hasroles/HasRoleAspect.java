package ru.stud.auc.annotation.hasroles;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import ru.stud.auc.common.enums.ClientRole;
import ru.stud.auc.exception.ForbiddenException;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Aspect
@Component
public class HasRoleAspect {

    @Before("@annotation(ru.stud.auc.annotation.hasroles.HasRole)")
    public void logMethodCall(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        HasRole hasRoles = method.getAnnotation(HasRole.class);

        String[] expectedRoles = hasRoles.value();
        List<String> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
        Optional<String> missingAuthority = Arrays.stream(expectedRoles).filter(r -> !authorities.contains(r)).findFirst();
        if (missingAuthority.isPresent()) {
            throw new ForbiddenException("Метод предназначен для роли %s".formatted(ClientRole.valueOf(missingAuthority.get()).getDescription()));
        }
        
    }
    
}
