package ru.stud.auc.flowdata.user;

import org.springframework.stereotype.Repository;
import ru.stud.auc.common.SoftDeleteAuditRepository;
import ru.stud.auc.common.enums.ClientRole;
import ru.stud.auc.flowdata.user.UserEntity;
import ru.stud.auc.flowdata.user.model.UserAdminView;

import javax.persistence.TypedQuery;
import java.util.*;

@Repository
public class UsersRepository extends SoftDeleteAuditRepository<UserEntity> {

    public List<UserAdminView> getAllUsersFiltered(int offset,
                                                   int size,
                                                   Optional<String> searchString,
                                                   Optional<ClientRole> role,
                                                   Optional<Boolean> isDeleted) {
        StringBuilder queryBuider = new StringBuilder("""
                   select new ru.stud.auc.flowdata.user.model.UserAdminView(
                   u.id,
                   u.login,
                   u.email,
                   u.role,
                   u.credit,
                   u.image,
                   u.insertUserId,
                   u.insertDatetime,
                   u.updateUserId,
                   u.updateDatetime,
                   u.isDeleted
                   )
                   from UserEntity u""");
        Map<String, Object> parameters = new HashMap<>();

        if (searchString.isPresent() || role.isPresent() || isDeleted.isPresent()) {
            String login = searchString.map(s -> '%' + s + '%').orElse("%");
            queryBuider.append(" where u.login like :login ");
            parameters.put("login", login);
        }

        if (role.isPresent()) {
            queryBuider.append(" and u.role = :role");
            parameters.put("role", role.get());
        }

        if (isDeleted.isPresent()) {
            queryBuider.append(" and u.isDeleted = :isDeleted");
            parameters.put("isDeleted", isDeleted.get());
        }

        TypedQuery<UserAdminView> query = em.createQuery(queryBuider.toString(), UserAdminView.class);
        for (Map.Entry<String, Object> parameter : parameters.entrySet()) {
            query.setParameter(parameter.getKey(), parameter.getValue());
        }

        return query.setFirstResult(offset).setMaxResults(size).getResultList();
    }

    public Optional<UserEntity> findByLogin(String login) {
        String q = "select u from UserEntity u where u.login = :login";
        return em.createQuery(q, UserEntity.class).setParameter("login", login).setMaxResults(1).getResultList().stream().findFirst();
    }

    public int setIsDeleted(UUID userId, boolean isDeleted) {
        String q = "update UserEntity u set u.isDeleted = :isDeleted where u.id = :userId";
        return em.createQuery(q).setParameter("userId", userId).setParameter("isDeleted", isDeleted).executeUpdate();
    }

}
