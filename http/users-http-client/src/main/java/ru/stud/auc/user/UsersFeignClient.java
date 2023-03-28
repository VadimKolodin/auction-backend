package ru.stud.auc.user;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import ru.stud.auc.dto.UserRegistrationDto;
import ru.stud.auc.flowdata.user.model.UserView;

import javax.validation.Valid;

@FeignClient(name = "usersFeignClient", url = "${http-clients.users}/api/user")
public interface UsersFeignClient {


    @PostMapping("/register")
    UserView create(@Valid @RequestBody UserRegistrationDto request);

}
