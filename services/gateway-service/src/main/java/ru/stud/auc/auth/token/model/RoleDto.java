package ru.stud.auc.auth.token.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import ru.stud.auc.common.enums.ClientRole;

import java.util.Collection;

@Data
@AllArgsConstructor
public class RoleDto {

    private ClientRole role;
}
