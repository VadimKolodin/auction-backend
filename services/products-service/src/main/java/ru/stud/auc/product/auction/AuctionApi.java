package ru.stud.auc.product.auction;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import ru.stud.auc.product.auction.model.*;

import java.util.UUID;

import static ru.stud.auc.product.auction.AuctionApi.AUCTION_API_PATH;


@RequestMapping(AUCTION_API_PATH)
@Tag(name = "Методы для работы  аукционами", description = AUCTION_API_PATH)
public interface AuctionApi {
    String AUCTION_API_PATH = "/api/auctions";

    @GetMapping("/admin")
    @Operation(summary = "Получение всех аукционов")
    AuctionSummaryDto getAuctionsAdmin();

    @GetMapping
    @Operation(summary = "Получение аукционов")
    AuctionSummaryDto getAll();

    @PostMapping
    @Operation(summary = "Создание аукциона")
    AuctionDto create(AuctionCreateDto dto);

    @GetMapping("/my")
    @Operation(summary = "Получение моих аукционов (покупатель-продавец)")
    AuctionSummaryDto getMyAuctions();

    @GetMapping("/{auctionId}}")
    @Operation(summary = "Получение аукциона")
    AuctionResponseDto getAuction();

    @PostMapping("/{auctionId}")
    @Operation(summary = "Проставление ставки на аукцион")
    void bet(@PathVariable UUID auctionId, @RequestBody AuctionBetDto auctionBetDto);

}
