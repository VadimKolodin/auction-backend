package ru.stud.auc.user.internal;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import ru.stud.auc.common.enums.ClientRole;
import ru.stud.auc.dto.UserDto;
import ru.stud.auc.user.UsersGetter;

import java.util.UUID;

@RestController
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class UsersInternalController implements UsersInternalApi {

    private final UsersGetter usersGetter;

    @Override
    public UserDto getUser(UUID userId) {
        return usersGetter.getUserInternal(userId);
    }

    @Override
    public ClientRole getUserRole(UUID userId) {
        return usersGetter.getUserInternal(userId).getRole();
    }
}
