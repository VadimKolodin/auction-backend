package ru.stud.auc.auth.model.auth;

import org.springframework.stereotype.Repository;
import ru.stud.auc.common.AbstractRepository;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@Repository
public class RefreshTokenRepository extends AbstractRepository<RefreshTokenEntity> {

    public Optional<RefreshTokenEntity> findByRefreshTokenValue(String token) {
        String q = "select t from RefreshTokenEntity t where t.token = :token";
        return em.createQuery(q, RefreshTokenEntity.class).setParameter("token", token).setMaxResults(1)
                 .getResultList()
                 .stream()
                 .findFirst();
    }

    @Transactional(value = Transactional.TxType.REQUIRED)
    public void deleteRefreshTokenByUserId(UUID userId) {
        String q = "delete from RefreshTokenEntity t where t.userId = :userId";
        em.createQuery(q).setParameter("userId", userId).executeUpdate();
    }

    @Transactional(value = Transactional.TxType.REQUIRED)
    public void deleteRefreshTokenById(UUID tokenId) {
        String q = "delete from RefreshTokenEntity t where t.id = :tokenId";
        em.createQuery(q).setParameter("tokenId", tokenId).executeUpdate();
    }

}
