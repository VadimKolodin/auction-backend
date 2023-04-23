package ru.stud.auc.product.auction;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import ru.stud.auc.annotation.hasroles.HasRole;
import ru.stud.auc.product.auction.model.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class AuctionsController implements AuctionApi {

    private final AuctionCreateService auctionCreateService;

    @Override
    @HasRole("ADMIN")
    public AuctionSummaryDto getAuctionsAdmin() {
        return null;
    }

    @Override
    @HasRole("SELLER")
    public AuctionSummaryDto getAll() {
        return null; //TODO: олучает те аукционы, в которых может участвовать (есть товар)
    }

    @Override
    @HasRole("CLIENT")
    public AuctionDto create(AuctionCreateDto dto) {
        return auctionCreateService.createAuction(dto);
    }

    @Override
    public AuctionSummaryDto getMyAuctions() {
        return null; //TODO: клиент получает выставленные им аукционы, продавец получает те, где делал хотя бы одну ставку
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
