package ru.stud.auc.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.stud.auc.dto.UserRegistrationDto;
import ru.stud.auc.flowdata.user.model.UserView;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UsersService {

    private final UsersInserter usersInserter;

    private final UsersUpdater usersUpdater;

    @Transactional
    public UserView createUser(UserRegistrationDto dto) {
        return usersInserter.createUser(dto);
    }

    @Transactional
    public void blockUser(UUID userId) {
        usersUpdater.setIsDeleted(userId, true);
    }

    @Transactional
    public void unblockUser(UUID userId) {
        usersUpdater.setIsDeleted(userId, false);
    }

}
