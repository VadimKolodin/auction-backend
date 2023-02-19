package ru.stud.auc.flowdata.user.account;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Getter
@Setter
@ToString
@Entity
@Table(name = "users_accounts")
public class AccountEntity {

    @Id
    @Column(name="id")
    private UUID id;

    @Column(name="user_id")
    private UUID userId;

    @Column(name="credit")
    private Integer credit;
}
