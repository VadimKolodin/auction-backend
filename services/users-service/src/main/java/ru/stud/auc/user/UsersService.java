package ru.stud.auc.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.stud.auc.dto.UserRegistrationDto;
import ru.stud.auc.flowdata.user.model.UserView;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class UsersService {

    private final UsersInserter usersInserter;

    @Transactional
    public UserView createUser(UserRegistrationDto dto) {
        return usersInserter.createUser(dto);
    }

}
