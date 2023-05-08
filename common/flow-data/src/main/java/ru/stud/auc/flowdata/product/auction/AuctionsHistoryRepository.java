package ru.stud.auc.flowdata.product.auction;

import org.springframework.stereotype.Repository;
import ru.stud.auc.common.AbstractRepository;
import ru.stud.auc.flowdata.product.auction.model.AuctionHistoryView;
import ru.stud.auc.flowdata.product.auction.model.AuctionProductView;

import java.util.List;
import java.util.UUID;

@Repository
public class AuctionsHistoryRepository extends AbstractRepository<AuctionsHistoryRepository> {


    public List<AuctionHistoryView> findByAuctionId(UUID auctionId) {
        String q = """
                select distinct new ru.stud.auc.flowdata.product.auction.model.AuctionHistoryView(
                h.id,
                h.auctionId,
                h.sellerId,
                h.price,
                h.requestDate
                ) from AuctionHistoryEntity h
                where h.auctionId = :auctionId 
                order by h.requestDate DESC
                """;
        return em.createQuery(q, AuctionHistoryView.class)
                .setParameter("auctionId", auctionId)
                .getResultList();

    }

}
