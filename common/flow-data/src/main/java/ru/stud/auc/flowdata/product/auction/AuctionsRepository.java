package ru.stud.auc.flowdata.product.auction;

import org.springframework.stereotype.Repository;
import ru.stud.auc.common.AbstractRepository;
import ru.stud.auc.flowdata.product.auction.model.AuctionProductView;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class AuctionsRepository extends AbstractRepository<AuctionEntity> {

    public Optional<AuctionProductView> find(UUID auctionId) {
        String q = """
                select new ru.stud.auc.flowdata.product.auction.model.AuctionProductView(
                a.id,
                a.sellerId,
                a.clientId,
                a.productId,
                p.cost,
                a.price,
                p.minCost,
                p.name,
                p.image,
                a.endTime
                ) from ProductEntity p 
                inner join AuctionEntity a
                    on p.id = a.productId
                where a.id = :auctionId
                order by a.endTime DESC
                """;
        return em.createQuery(q, AuctionProductView.class)
                .setParameter("auctionId", auctionId)
                .getResultList()
                .stream()
                .findFirst();

    }

    public List<AuctionProductView> findAll(boolean isActive, int size, int offset) {
        String q = """
                select new ru.stud.auc.flowdata.product.auction.model.AuctionProductView(
                a.id,
                a.sellerId,
                a.clientId,
                a.productId,
                p.cost,
                a.price,
                p.minCost,
                p.name,
                p.image,
                a.endTime
                ) from AuctionEntity a 
                inner join ProductEntity p
                    on p.id = a.productId
                where (a.endTime > :now) = :isActive
                order by a.endTime DESC
                """;
        return em.createQuery(q, AuctionProductView.class)
                .setParameter("isActive", isActive)
                .setMaxResults(size)
                .setFirstResult(offset)
                .getResultList();

    }

    public List<AuctionProductView> findCustomerAuctions(UUID clientId, int size, int offset) {
        String q = """
                select new ru.stud.auc.flowdata.product.auction.model.AuctionProductView(
                a.id,
                a.sellerId,
                a.clientId,
                a.productId,
                p.cost,
                a.price,
                p.minCost,
                p.name,
                p.image,
                a.endTime
                ) from AuctionEntity a 
                inner join ProductEntity p
                    on p.id = a.productId
                where a.clientId = :clientId
                order by a.endTime DESC
                """;
        return em.createQuery(q, AuctionProductView.class)
                .setParameter("clientId", clientId)
                .setMaxResults(size)
                .setFirstResult(offset)
                .getResultList();

    }

    public List<AuctionProductView> findSellersAuctions(UUID sellerId, int size, int offset) {
        String q = """
                select distinct new ru.stud.auc.flowdata.product.auction.model.AuctionProductView(
                a.id,
                a.sellerId,
                a.clientId,
                a.productId,
                p.cost,
                a.price,
                p.minCost,
                p.name,
                p.image,
                a.endTime
                ) from AuctionEntity a 
                inner join ProductEntity p
                    on p.id = a.productId
                inner join AuctionHistoryEntity his
                where a.sellerId = :sellerId
                    or his.sellerId = :sellerId 
                order by a.endTime DESC
                """;
        return em.createQuery(q, AuctionProductView.class)
                .setParameter("sellerId", sellerId)
                .setMaxResults(size)
                .setFirstResult(offset)
                .getResultList();

    }

}
