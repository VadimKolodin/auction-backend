package ru.stud.auc.user;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import ru.stud.auc.dto.UserRegistrationDto;
import ru.stud.auc.flowdata.user.model.UserView;

@RestController
@RequiredArgsConstructor
public class UsersController implements UsersApi {

    private final UsersService productsService;

    @Override
    public UserView create(UserRegistrationDto request) {
        return productsService.createUser(request);
    }
}
