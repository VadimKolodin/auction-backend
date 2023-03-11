package ru.stud.auc.users;

import ru.stud.auc.common.SoftDeleteEntity;
import ru.stud.auc.common.enums.ClientRole;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@ToString
@Entity
@Table(name = "users")
public class UserAuthEntity extends SoftDeleteEntity {

    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "login", unique = true)
    private String login;

    @Column(name="email", unique = true)
    private String email;

    @Column(name="password")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name="role")
    private ClientRole role;


}
