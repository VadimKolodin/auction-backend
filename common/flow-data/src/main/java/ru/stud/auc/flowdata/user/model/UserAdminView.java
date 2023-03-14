package ru.stud.auc.flowdata.user.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.stud.auc.common.enums.ClientRole;

import javax.persistence.Column;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
public class UserAdminView {

    private UUID id;

    private String login;

    private String email;

    private ClientRole role;

    private Integer credit;

    private byte[] image;

    private UUID insertUserId;

    private LocalDateTime insertDatetime;

    private UUID updateUserId;

    private LocalDateTime updateDatetime;

    private Boolean isDeleted;

}
