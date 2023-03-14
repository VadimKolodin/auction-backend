package ru.stud.auc.flowdata.product.model;

import lombok.Data;
import ru.stud.auc.common.enums.SubTag;
import ru.stud.auc.common.enums.Tag;

import javax.persistence.Column;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class ProductAdminView {
    private final UUID id;

    private final String name;

    private final String description;

    private final byte[] image;

    private final Tag tag;

    private final SubTag subTag;

    private final Integer cost;

    private Boolean isDeleted;

    private UUID insertUserId;

    private LocalDateTime insertDatetime;

    private UUID updateUserId;

    private LocalDateTime updateDatetime;
}
