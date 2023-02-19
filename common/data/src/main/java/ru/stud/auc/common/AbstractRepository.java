package ru.stud.auc.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.GenericTypeResolver;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

public abstract class AbstractRepository <T> {

    protected final Logger log = LoggerFactory.getLogger(getClass());

    protected final Class<T> clazz = (Class<T>) GenericTypeResolver.resolveTypeArgument(getClass(), AbstractRepository.class);

    @PersistenceContext
    protected EntityManager em;

    public T persist(T e) {
        em.persist(e);
        log.debug("Сохранение объекта: {}", e);
        return e;
    }

    public Optional<T> findById(Object id) {
        return Optional.ofNullable(em.find(clazz, id));
    }
}
