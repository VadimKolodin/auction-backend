package ru.stud.auc.flowdata.product.order;


import org.springframework.stereotype.Repository;
import ru.stud.auc.common.AbstractRepository;
import ru.stud.auc.common.enums.OrderStatus;
import ru.stud.auc.flowdata.product.auction.AuctionEntity;
import ru.stud.auc.flowdata.product.auction.model.AuctionProductView;
import ru.stud.auc.flowdata.product.order.model.OrderView;

import java.util.List;
import java.util.UUID;

@Repository
public class OrderRepository extends AbstractRepository<OrderEntity> {

    public List<OrderView> findSellerOrders(UUID clientId, int size, int offset) {
        String q = """
                select new ru.stud.auc.flowdata.product.order.model.OrderView(
                a.id,
                a.sellerId,
                a.clientId,
                a.productId,
                a.price,
                a.status,
                p.name,
                p.image
                ) from OrderEntity a
                inner join ProductEntity p
                    on p.id = a.productId
                where a.clientId = :clientId
                order by a.status
                """;
        return em.createQuery(q, OrderView.class)
                .setParameter("clientId", clientId)
                .setMaxResults(size)
                .setFirstResult(offset)
                .getResultList();
    }

    public int setStatus(UUID orderId, OrderStatus orderStatus){
        String q = "update OrderEntity o set o.status =: orderStatus " +
                " where o.id =: orderId";

        return em.createQuery(q)
                .setParameter("orderId", orderId)
                .setParameter("orderStatus", orderStatus)
                .executeUpdate();
    }

}
