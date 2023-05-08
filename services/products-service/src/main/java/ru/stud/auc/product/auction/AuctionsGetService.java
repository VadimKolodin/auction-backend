package ru.stud.auc.product.auction;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.stud.auc.auth.util.AuthenticationUtils;
import ru.stud.auc.common.enums.ClientRole;
import ru.stud.auc.consts.StringConsts;
import ru.stud.auc.exception.NotFoundException;
import ru.stud.auc.flowdata.product.auction.model.AuctionProductView;
import ru.stud.auc.product.auction.model.AuctionHistoryDto;
import ru.stud.auc.product.auction.model.AuctionResponseDto;
import ru.stud.auc.product.auction.model.AuctionSummaryDto;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuctionsGetService {

    private final AuctionsMapper auctionsMapper;

    private final AuctionsGetter auctionsGetter;


    public AuctionResponseDto get(UUID auctionId) {
        AuctionProductView auctionProductView = auctionsGetter.get(auctionId);
        UUID userId = AuthenticationUtils.getUserId();
        if (!Objects.equals(userId, auctionProductView.getClientId()) && !AuthenticationUtils.isSeller()) {
            throw new NotFoundException(StringConsts.Auction.NOT_FOUND);
        }
        List<AuctionHistoryDto> historyDtos = auctionsGetter.getHistory(auctionId).stream().map(auctionsMapper::toHistoryDto).toList();
        return auctionsMapper.toDto(auctionProductView, historyDtos);


    }

    public List<AuctionSummaryDto> getAll(boolean isActive, int page, int size) {
        return auctionsGetter.getAll(isActive, page, size).stream().map(auctionsMapper::toSummaryDto).toList();
    }

    public List<AuctionSummaryDto> getMyAuctions(int page, int size) {
        ClientRole clientRole = AuthenticationUtils.getRole();
        UUID userId = AuthenticationUtils.getUserId();
        List<AuctionProductView> auctions = switch (clientRole) {
            case ADMIN -> auctionsGetter.getAll(true, page, size);
            case CLIENT -> auctionsGetter.getCustomers(userId, page, size);
            case SELLER -> auctionsGetter.getSellers(userId, page, size);
        };
        return auctions.stream().map(auctionsMapper::toSummaryDto).toList();

    }

}
