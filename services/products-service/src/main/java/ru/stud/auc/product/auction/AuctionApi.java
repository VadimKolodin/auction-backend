package ru.stud.auc.product.auction;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import ru.stud.auc.annotation.hasroles.HasRole;
import ru.stud.auc.product.auction.model.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.UUID;

import static ru.stud.auc.product.auction.AuctionApi.AUCTION_API_PATH;


@RequestMapping(AUCTION_API_PATH)
@Tag(name = "Методы для работы  аукционами", description = AUCTION_API_PATH)
public interface AuctionApi {
    String AUCTION_API_PATH = "/api/auctions";

    @GetMapping("/admin")
    @Operation(summary = "Получение всех аукционов")
    List<AuctionSummaryDto> getAuctionsAdmin(
            @RequestParam(name = "isActive", required = true) boolean isActive,
            @RequestParam(name = "page", required = true) @Min(0) int page,
            @RequestParam(name = "size", required = true)  @Min(1) @Max(100) int size);

    @GetMapping
    @Operation(summary = "Получение доступных аукционов")
    @HasRole("SELLER")
    List<AuctionSummaryDto> getAvailable();

    @PostMapping
    @Operation(summary = "Создание аукциона")
    AuctionDto create(@RequestBody AuctionCreateDto dto);

    @GetMapping("/my")
    @Operation(summary = "Получение моих аукционов (покупатель-продавец)")
    List<AuctionSummaryDto> getMyAuctions(@RequestParam(name = "page", required = true) @Min(0) int page,
                                          @RequestParam(name = "size", required = true)  @Min(1) @Max(100) int size);

    @GetMapping("/{auctionId}")
    @Operation(summary = "Получение аукциона")
    AuctionResponseDto getAuction();

    @PostMapping("/{auctionId}")
    @Operation(summary = "Проставление ставки на аукцион")
    void bet(@PathVariable UUID auctionId, @RequestBody AuctionBetDto auctionBetDto);

}
