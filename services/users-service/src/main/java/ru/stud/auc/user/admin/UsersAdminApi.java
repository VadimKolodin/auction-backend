package ru.stud.auc.user.admin;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.stud.auc.common.enums.ClientRole;
import ru.stud.auc.flowdata.user.model.UserAdminView;

import javax.validation.constraints.Min;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Validated
@RequestMapping(UsersAdminApi.USERS_ADMIN_API_PATH)
@Tag(name = "Методы для работы с пользователями для админа", description = UsersAdminApi.USERS_ADMIN_API_PATH)
public interface UsersAdminApi {

    String USERS_ADMIN_API_PATH = "/api/admin/user";

    @DeleteMapping("/{userId}")
    @Operation(summary = "Заблокировать пользователя")
    void block(@PathVariable UUID userId);

    @PatchMapping("/{userId}")
    @Operation(summary = "Разблокировать пользователя")
    void unblock(@PathVariable UUID userId);

    @GetMapping
    @Operation(summary = "Получить список пользователей")
    List<UserAdminView> getUsers(@RequestParam @Min(0) int page,
                                 @RequestParam @Min(1) int size,
                                 @RequestParam Optional<String> searchString,
                                 @RequestParam Optional<ClientRole> role,
                                 @RequestParam Optional<Boolean> isDeleted);

}
