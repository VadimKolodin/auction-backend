package ru.stud.auc.common;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@Getter
@Setter
@ToString
@MappedSuperclass
public class SoftDeleteEntity extends UpdateAuditEntity {

    @Column(name = "is_deleted")
    private Boolean isDeleted;
}
