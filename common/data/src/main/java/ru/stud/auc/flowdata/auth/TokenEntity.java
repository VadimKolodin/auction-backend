package ru.stud.auc.flowdata.auth;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@ToString
@Entity
@Table(name = "tokens")
public class TokenEntity {

    @Id
    private UUID id;

    @Column(unique = true)
    private UUID userId;

    @Column(unique = true)
    public String token;


}
