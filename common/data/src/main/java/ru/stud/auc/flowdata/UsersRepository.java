package ru.stud.auc.flowdata;

import org.springframework.stereotype.Repository;
import ru.stud.auc.common.SoftDeleteAuditRepository;
import ru.stud.auc.flowdata.user.UserEntity;

import java.util.Optional;

@Repository
public class UsersRepository extends SoftDeleteAuditRepository<UserEntity> {

    public Optional<UserEntity> findByLogin(String login) {
        String q = "select u from UserEntity u where u.login = :login";
        return em.createQuery(q, UserEntity.class).setParameter("login", login).setMaxResults(1)
                 .getResultList()
                 .stream()
                 .findFirst();
    }

}
