package flowdata.client;

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
@Table(name = "client")
public class ClientEntity extends SoftDeleteEntity {

    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "login", unique = true)
    private String login;

    @Column(name="password")
    private String password;

    @Column(name="email", unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name="role")
    private ClientRole role;

    @Column(name="credit")
    private Integer credit;

}
