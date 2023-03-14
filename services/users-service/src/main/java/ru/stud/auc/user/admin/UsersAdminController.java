package ru.stud.auc.user.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import ru.stud.auc.annotation.hasroles.HasRole;
import ru.stud.auc.common.enums.ClientRole;
import ru.stud.auc.flowdata.user.model.UserAdminView;
import ru.stud.auc.user.UsersGetter;
import ru.stud.auc.user.UsersService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class UsersAdminController implements UsersAdminApi {

    private final UsersGetter usersGetter;

    private final UsersService usersService;

    @Override
    @HasRole("ADMIN")
    public void block(UUID userId) {
        usersService.blockUser(userId);
    }

    @Override
    @HasRole("ADMIN")
    public void unblock(UUID userId) {
        usersService.unblockUser(userId);
    }

    @Override
    @HasRole("ADMIN")
    public List<UserAdminView> getUsers(int page, int size, Optional<String> searchString, Optional<ClientRole> role, Optional<Boolean> isDeleted) {
        return usersGetter.getUsers(page, size, searchString, role, isDeleted);
    }
}
