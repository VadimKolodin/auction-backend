package ru.stud.auc.flowdata.user;

import org.hibernate.annotations.Type;
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
public class UserEntity extends SoftDeleteEntity {

    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "login", unique = true)
    private String login;

    @Column(name="email", unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name="role")
    private ClientRole role;

    @Column(name="credit")
    private Integer credit;

    @Type(type="org.hibernate.type.MaterializedBlobType")
    @Column(name = "image")
    private byte[] image;


}
