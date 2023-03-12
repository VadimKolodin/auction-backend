package ru.stud.auc.product;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.stud.auc.annotation.hasroles.HasRole;

@RestController
@RequestMapping("/api")
public class TestController {

    @GetMapping("/anonymous")
    public String getAnonymousInfo() {
        return "Anonymous";
    }

    @GetMapping("/client")
    @HasRole("CLIENT")
    public String getUserInfo() {
        return "client info";
    }

    @GetMapping("/admin")
    @HasRole("ADMIN")
    public String getAdminInfo() {
        return "admin info";
    }

    @GetMapping("/seller")
    @HasRole("SELLER")
    public String getServiceInfo() {
        return "seller info";
    }

    @GetMapping("/me")
    public Object getMe() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.toString();
    }
}