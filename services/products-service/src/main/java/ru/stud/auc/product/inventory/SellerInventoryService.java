package ru.stud.auc.product.inventory;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.stud.auc.product.ProductsGetter;
import ru.stud.auc.product.model.SellerProductsDto;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SellerInventoryService {

    private final ProductsGetter productsGetter;

    private final InventoryMapper inventoryMapper;

    private final SellerInventoryGetter sellerInventoryGetter;

    private final SellerInventoryUpdater sellerInventoryUpdater;

    private final SellerInventoryDeleter sellerInventoryDeleter;

    public List<SellerProductsDto> getSellerInventory(UUID sellerId) {
        return sellerInventoryGetter.getSellerInventory(sellerId).stream().map(inventoryMapper::toInventoryDto).toList();
    }

    @Transactional
    public void updateAmount(UUID productId, UUID sellerId, int amount) {
        // todo: check on existing sellerId
        // Запись есть ? меняем : добавляем
        // меняем на 0 ? удаляем : менем кол-во
    }



}
