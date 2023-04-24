package ru.stud.auc.flowdata.product.inventory;


import org.springframework.stereotype.Repository;
import ru.stud.auc.common.AbstractRepository;
import ru.stud.auc.flowdata.product.inventory.model.InventoryProductView;

import java.util.List;
import java.util.UUID;


@Repository
public class InventoryRepository extends AbstractRepository<InventoryEntity> {
    public List<InventoryProductView> findSellerInventory(UUID sellerId) {
        String q = """
                   select new ru.stud.auc.flowdata.product.inventory.model.InventoryProductView(
                   c.id.productId,
                   p.name,
                   p.description,
                   c.amount,
                   p.cost,
                   p.image
                   )
                   from InventoryEntity c 
                   inner join ProductEntity p
                     on c.id.productId = p.id
                   where
                     c.id.sellerId = :sellerId and
                     p.isDeleted = false
                   """;
        return em.createQuery(q, InventoryProductView.class)
                .setParameter("sellerId", sellerId)
                .getResultList();
    }

    public int setAmount(UUID productId, UUID sellerId, int amount) {
        String q = "update InventoryEntity" +
                " c set c.amount = :amount where c.id.productId = :productId and c.id.sellerId = :sellerId";
        return em.createQuery(q)
                .setParameter("productId", productId)
                .setParameter("sellerId", sellerId)
                .setParameter("amount", amount)
                .executeUpdate();
    }

    public void deleteInventoryProduct(UUID productId, UUID sellerId) {
        String q = "delete from InventoryEntity c where c.id.productId = :productId and c.id.sellerId = :sellerId";
        em.createQuery(q)
                .setParameter("productId", productId)
                .setParameter("sellerId", sellerId)
                .executeUpdate();
    }

    public void deleteAllInventory(UUID sellerId) {
        String q = "delete from InventoryEntity c where c.id.sellerId = :sellerId";
        em.createQuery(q)
                .setParameter("sellerId", sellerId)
                .executeUpdate();
    }
}
