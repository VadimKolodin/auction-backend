package ru.stud.auc.product.auction;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.stud.auc.consts.StringConsts;
import ru.stud.auc.exception.NotFoundException;
import ru.stud.auc.flowdata.product.auction.AuctionsHistoryRepository;
import ru.stud.auc.flowdata.product.auction.AuctionsRepository;
import ru.stud.auc.flowdata.product.auction.model.AuctionHistoryView;
import ru.stud.auc.flowdata.product.auction.model.AuctionProductView;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class AuctionsGetter {

    private final AuctionsRepository auctionsRepository;

    private final AuctionsHistoryRepository auctionsHistoryRepository;

    public AuctionProductView get(UUID auctionId) {
        return auctionsRepository.find(auctionId).orElseThrow(() -> new NotFoundException(StringConsts.Auction.NOT_FOUND));
    }
    public List<AuctionProductView> getAll(boolean isActive, int page, int size) {
        int offset = page*size;
        return auctionsRepository.findAll(isActive, size, offset);
    }

    public List<AuctionProductView> getCustomers(UUID clientId, int page, int size) {
        int offset = page*size;
        return auctionsRepository.findCustomerAuctions(clientId, size, offset);
    }

    public List<AuctionProductView> getSellers(UUID sellerId, int page, int size) {
        int offset = page*size;
        return auctionsRepository.findSellersAuctions(sellerId, size, offset);
    }

    public List<AuctionHistoryView> getHistory(UUID auctionId) {
        return auctionsHistoryRepository.findByAuctionId(auctionId);
    }
}
