package ru.stud.auc.product.sellerInventory;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.stud.auc.auth.util.AuthenticationUtils;
import ru.stud.auc.flowdata.product.cart.model.CartProductView;
import ru.stud.auc.product.ProductsGetter;
import ru.stud.auc.product.cart.model.CartRequestDto;
import ru.stud.auc.product.model.SellerProductsDto;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SellerInventoryService {
    private final SellerInventoryGetter sellerInventoryGetter;
    private final SellerInventoryUpdater sellerInventoryUpdater;

    private final SellerInventoryDeleter SellerInventoryDeleter;

    private final ProductsGetter productsGetter;

    public SellerProductsDto getSellerInventory(UUID sellerId) {
        // todo: check on existing sellerId
        SellerProductsDto sellerProductsDto = new SellerProductsDto();
        sellerProductsDto.setSellerId(sellerId);
        sellerProductsDto.setProducts(sellerInventoryGetter.getSellerInventory(sellerId));
        return  sellerProductsDto;
    }

    public void setSellerInventoryProductAmount(UUID productId, UUID sellerId, int amount) {
        // todo: check on existing sellerId
        if (amount < 0) throw new RuntimeException("Количество товара не может быть меньше 0 ");
        sellerInventoryUpdater.setAmount(productId, sellerId, amount);
    }


    public void deleteFromSellerInventoryProduct(UUID productId, UUID sellerId) {
        // todo: check on existing sellerId
        productsGetter.checkExistsAndNotDeleted(productId);
        SellerInventoryDeleter.deleteFromSellerInventoryProduct(productId, sellerId);
    }
}
