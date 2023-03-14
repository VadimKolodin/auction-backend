package ru.stud.auc.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.stud.auc.dto.UserRegistrationDto;
import ru.stud.auc.flowdata.user.UserEntity;
import ru.stud.auc.flowdata.user.UsersRepository;
import ru.stud.auc.flowdata.user.model.UserView;

import javax.transaction.Transactional;

@Component
@RequiredArgsConstructor
public class UsersInserter {

    private final UsersMapper usersMapper;

    private final UsersRepository usersRepository;

    @Transactional
    public UserView createUser(UserRegistrationDto userRegistrationDto) {
        UserEntity user = usersMapper.toEntity(userRegistrationDto);
        return usersMapper.toView(usersRepository.persistWithoutUser(user));
    }
}
