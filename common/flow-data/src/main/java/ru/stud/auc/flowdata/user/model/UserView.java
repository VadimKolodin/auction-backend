package ru.stud.auc.flowdata.user.model;

import lombok.Data;
import ru.stud.auc.common.enums.ClientRole;

import java.util.UUID;

@Data
public class UserView {

    private UUID id;

    private String login;

    private String email;

    private ClientRole role;

    private Integer credit;

    private byte[] image;

}
