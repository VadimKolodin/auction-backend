package ru.stud.auc.product.auction;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import ru.stud.auc.annotation.hasroles.HasRole;
import ru.stud.auc.product.auction.model.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class AuctionsController implements AuctionApi {

    private final AuctionCreateService auctionCreateService;

    private final AuctionsGetService auctionsGetService;

    @Override
    @HasRole("ADMIN")
    public List<AuctionSummaryDto> getAuctionsAdmin(boolean isActive, int page, int size) {
        return auctionsGetService.getAll(isActive, page, size);
    }

    @Override
    @HasRole("SELLER")
    public List<AuctionSummaryDto> getAvailable() {
        return null; //TODO: олучает те аукционы, в которых может участвовать (есть товар)
    }

    @Override
    @HasRole("CLIENT")
    public AuctionDto create(AuctionCreateDto dto) {
        return auctionCreateService.createAuction(dto);
    }

    @Override
    public List<AuctionSummaryDto> getMyAuctions(int page, int size) {
        return auctionsGetService.getMyAuctions(page, size);
    }

    @Override
    public AuctionResponseDto getAuction() {
        return null;
    }

    @Override
    @HasRole("SELLER")
    public void bet(UUID auctionId, AuctionBetDto auctionBetDto) {

    }
}
