package ru.stud.auc.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.stud.auc.consts.StringConsts;
import ru.stud.auc.exception.NotFoundException;
import ru.stud.auc.flowdata.user.UsersRepository;

import javax.transaction.Transactional;
import java.util.UUID;


@Component
@RequiredArgsConstructor
public class UsersUpdater {

    private final UsersRepository usersRepository;

    @Transactional
    public void setIsDeleted(UUID userId, boolean isDeleted) {
        int result = usersRepository.setIsDeleted(userId, isDeleted);
        if (result < 1) {
            throw new NotFoundException(StringConsts.User.NOT_FOUND);
        }
    }

}
