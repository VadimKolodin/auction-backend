package ru.stud.auc.product.auction;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.stud.auc.common.enums.OrderStatus;
import ru.stud.auc.product.auction.model.AuctionSummaryDto;
import ru.stud.auc.product.order.OrderInserter;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuctionSheduler {
    
    private final AuctionsGetService auctionsGetService;
    private final OrderInserter orderInserter;
    
    @Scheduled(fixedRate = 1 * 1000 * 60)
    @Transactional
    public void makeOrderFromFinishedAuction() {
        // todo: верно ли я понимаю, что они будет всегда вытягивать 100 первых и клепать из них заказы - не хорошо. Может быть добавить новое поле OrderIsDone: False
        List<AuctionSummaryDto> auctionSummaryDtoList = 
                auctionsGetService.getAll(false, 0, 100);
        for (AuctionSummaryDto auction:
                auctionSummaryDtoList) {
            orderInserter.createOrder(auction.getId(),
                    auction.getProductId(),
                    auction.getClientId(),
                    auction.getSellerId(),
                    auction.getCurrentPrice(),
                    OrderStatus.START);
        }
    }
}
