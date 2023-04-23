package ru.stud.auc.product.auction;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.stud.auc.flowdata.product.auction.AuctionEntity;
import ru.stud.auc.flowdata.product.auction.AuctionsRepository;
import ru.stud.auc.product.auction.model.AuctionDto;

import java.time.LocalDateTime;
import java.time.temporal.TemporalAmount;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class AuctionInserter {

    private final AuctionsMapper auctionsMapper;

    private final AuctionsRepository auctionsRepository;

    public AuctionDto createAuction(UUID productId, UUID clientId, int price, long duration, TimeUnit timeUnit) {
        AuctionEntity auction = new AuctionEntity();

        auction.setId(UUID.randomUUID());
        auction.setProductId(productId);
        auction.setClientId(clientId);
        auction.setPrice(price);
        LocalDateTime now = LocalDateTime.now();
        auction.setEndTime(now.plus(duration, timeUnit.toChronoUnit()));

        auctionsRepository.persist(auction);

        return auctionsMapper.toSimpleDto(auction);
    }

}
