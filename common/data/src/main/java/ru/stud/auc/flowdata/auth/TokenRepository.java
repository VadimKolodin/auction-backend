package ru.stud.auc.flowdata.auth;

import org.springframework.stereotype.Repository;
import ru.stud.auc.common.AbstractRepository;
import ru.stud.auc.flowdata.auth.TokenEntity;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@Repository
public class TokenRepository extends AbstractRepository<TokenEntity> {

    public Optional<TokenEntity> findByTokenValue(String token) {
        String q = "select t from TokenEntity t where t.token = :token";
        return em.createQuery(q, TokenEntity.class).setParameter("token", token).setMaxResults(1)
                 .getResultList()
                 .stream()
                 .findFirst();
    }

    @Transactional(value = Transactional.TxType.REQUIRED)
    public void deleteTokenByUserId(UUID userId) {
        String q = "delete from TokenEntity t where t.userId = :userId";
        em.createQuery(q).setParameter("userId", userId).executeUpdate();
    }

    @Transactional(value = Transactional.TxType.REQUIRED)
    public void deleteTokenById(UUID tokenId) {
        String q = "delete from TokenEntity t where t.id = :tokenId";
        em.createQuery(q).setParameter("tokenId", tokenId).executeUpdate();
    }

}
