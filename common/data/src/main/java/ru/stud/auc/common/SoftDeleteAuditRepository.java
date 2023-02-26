package ru.stud.auc.common;

import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@Slf4j
public class SoftDeleteAuditRepository <T extends SoftDeleteEntity> extends AuditRepository<T> {

    @Override
    public T persist(T e) {
        e.setIsDeleted(false);
        return super.persist(e);
    }

    public void deleteById(UUID id) {
        em.find(clazz, id).setIsDeleted(true);
        em.flush();
    }
}
