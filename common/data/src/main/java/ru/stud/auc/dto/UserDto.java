package ru.stud.auc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.stud.auc.common.enums.ClientRole;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
public class UserDto {

    private UUID id;

    private String login;

    private String email;

    private ClientRole role;

    private Integer credit;

    private Boolean isDeleted;

}
