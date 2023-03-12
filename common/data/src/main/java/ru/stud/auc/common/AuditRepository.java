package ru.stud.auc.common;


import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDateTime;
import java.util.UUID;


public class AuditRepository <T extends UpdateAuditEntity> extends AbstractRepository<T> {

    public T persist(T e) {
        LocalDateTime now = LocalDateTime.now();
        Object userId = SecurityContextHolder.getContext().getAuthentication().getCredentials();
        e.setInsertDatetime(now);
        e.setInsertUserId((UUID) userId);
        e.setUpdateDatetime(now);
        e.setUpdateUserId((UUID) userId);
        return super.persist(e);
    }

    public T persistWithoutUser(T e) {
        LocalDateTime now = LocalDateTime.now();
        e.setInsertDatetime(now);
        e.setInsertUserId(UUID.fromString("00000000-0000-0000-0000-000000000000"));
        e.setUpdateDatetime(now);
        e.setUpdateUserId(UUID.fromString("00000000-0000-0000-0000-000000000000"));
        return super.persist(e);
    }

    public void flush() {
        em.flush();
    }


}
