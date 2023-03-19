package ru.stud.auc.flowdata.product.cart;

import org.springframework.stereotype.Repository;
import ru.stud.auc.common.AbstractRepository;
import ru.stud.auc.flowdata.product.cart.model.CartProductView;

import java.util.List;
import java.util.UUID;

@Repository
public class CartsRepository extends AbstractRepository<CartEntity> {

    public List<CartProductView> getCart(UUID userId) {
        String q = """
                   select new ru.stud.auc.flowdata.product.cart.model.CartProductView(
                   c.id.productId,
                   p.name,
                   c.amount,
                   p.cost,
                   p.image
                   )
                   from CartEntity c inner join ProductEntity p
                   on c.id.productId = p.id
                   where 
                     c.id.clientId = :userId and
                     p.isDeleted = false
                   """;
        return em.createQuery(q, CartProductView.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    public int setAmount(UUID productId, UUID userId, int amount) {
        String q = "update CartEntity c set c.amount = :amount where c.id.productId = :productId and c.id.clientId = :userId";
        return em.createQuery(q)
                 .setParameter("productId", productId)
                 .setParameter("userId", userId)
                 .setParameter("amount", amount)
                 .executeUpdate();
    }

    public int deleteFromCart(UUID productId, UUID userId) {
        String q = "delete from CartEntity c where c.id.productId = :productId and c.id.clientId = :userId";
        return em.createQuery(q)
                 .setParameter("productId", productId)
                 .setParameter("userId", userId)
                 .executeUpdate();
    }

    public int deleteAllFromCart(UUID userId) {
        String q = "delete from CartEntity c where c.id.clientId = :userId";
        return em.createQuery(q)
                 .setParameter("userId", userId)
                 .executeUpdate();
    }

}
