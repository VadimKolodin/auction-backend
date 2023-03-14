package ru.stud.auc.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.stud.auc.common.enums.ClientRole;
import ru.stud.auc.consts.StringConsts;
import ru.stud.auc.dto.UserDto;
import ru.stud.auc.exception.NotFoundException;
import ru.stud.auc.flowdata.user.UserEntity;
import ru.stud.auc.flowdata.user.UsersRepository;
import ru.stud.auc.flowdata.user.model.UserAdminView;
import ru.stud.auc.flowdata.user.model.UserView;

import java.util.List;
import java.util.Optional;
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

    public UserDto getUserInternal(UUID userId) {
        UserEntity user = usersRepository.findById(userId)
                                         .orElseThrow(() -> new NotFoundException(StringConsts.User.NOT_FOUND));
        return usersMapper.toDto(user);
    }

    public List<UserAdminView> getUsers(int page,
                                        int size,
                                        Optional<String> searchString,
                                        Optional<ClientRole> role,
                                        Optional<Boolean> isDeleted) {
        int offset = page*size;
        return usersRepository.getAllUsersFiltered(offset, size, searchString, role, isDeleted);
    }


}
