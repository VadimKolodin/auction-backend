package ru.stud.auc.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.stud.auc.consts.StringConsts;
import ru.stud.auc.exception.NotFoundException;
import ru.stud.auc.flowdata.user.UserEntity;
import ru.stud.auc.flowdata.user.UsersRepository;
import ru.stud.auc.flowdata.user.model.UserView;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UsersGetter {

    private final UsersMapper usersMapper;

    private final UsersRepository usersRepository;

    public UserView getUser(UUID userId) {
        UserEntity user = usersRepository.findById(userId)
                                            .orElseThrow(() -> new NotFoundException(StringConsts.User.NOT_FOUND));
        return usersMapper.toView(user);
    }


}
