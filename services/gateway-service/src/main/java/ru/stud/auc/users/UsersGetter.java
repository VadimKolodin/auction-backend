package ru.stud.auc.users;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.stud.auc.auth.model.UserAuthPojo;
import ru.stud.auc.exception.NotFoundException;
import ru.stud.auc.flowdata.UsersRepository;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UsersGetter {

    private final UsersRepository repository;
    private final UsersAuthMapper mapper;

    public UserAuthPojo getAuthPojoById(UUID id) {
        return mapper.toPojo(repository.findById(id).orElseThrow(() -> new NotFoundException("Пользователь не найден")));
    }
}
