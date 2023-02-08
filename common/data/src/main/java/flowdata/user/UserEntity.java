package flowdata.user;

import common.SoftDeleteEntity;
import common.enums.ClientRole;
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

    @Column(name="password")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name="role")
    private ClientRole role;

    @Column(name="credit")
    private Integer credit;

    //image

}
