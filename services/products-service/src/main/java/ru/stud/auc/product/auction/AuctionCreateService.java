package ru.stud.auc.product.auction;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.stud.auc.auth.util.AuthenticationUtils;
import ru.stud.auc.flowdata.product.model.ProductView;
import ru.stud.auc.product.ProductsGetter;
import ru.stud.auc.product.auction.model.AuctionCreateDto;
import ru.stud.auc.product.auction.model.AuctionDto;
import ru.stud.auc.product.cart.CartsUpdater;

import javax.transaction.Transactional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class AuctionCreateService {

    //TODO:вынести в env
    private static final long AUC_DURATION = 10;

    private final CartsUpdater cartsUpdater;

    private final ProductsGetter productsGetter;

    private final AuctionInserter auctionInserter;

    @Transactional
    public AuctionDto createAuction(AuctionCreateDto auctionCreateDto) {
        UUID clientId = AuthenticationUtils.getUserId();
        UUID productId = auctionCreateDto.getProductId();

        cartsUpdater.decrementAmount(productId, clientId);
        ProductView productView = productsGetter.getProduct(productId);
        return auctionInserter.createAuction(productId, clientId, productView.getCost(), AUC_DURATION, TimeUnit.MINUTES);
    }

}
