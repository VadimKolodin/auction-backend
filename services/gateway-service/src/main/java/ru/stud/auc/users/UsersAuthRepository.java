package ru.stud.auc.users;

import org.springframework.stereotype.Repository;
import ru.stud.auc.common.SoftDeleteAuditRepository;

import java.util.Optional;

@Repository
public class UsersAuthRepository extends SoftDeleteAuditRepository<UserAuthEntity> {

    public Optional<UserAuthEntity> findByLogin(String login) {
        String q = "select u from UserAuthEntity u where u.login = :login";
        return em.createQuery(q, UserAuthEntity.class).setParameter("login", login).setMaxResults(1)
                 .getResultList()
                 .stream()
                 .findFirst();
    }

}
